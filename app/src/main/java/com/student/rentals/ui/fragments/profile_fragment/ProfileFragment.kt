package com.student.rentals.ui.fragments.profile_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.student.Utils.Constants
import com.student.Utils.GlideApp
import com.student.Utils.SharedPreferencesManager
import com.student.models.DUserData
import com.student.rentals.R
import com.student.rentals.databinding.ProfileFragmentBinding
import com.student.rentals.ui.activities.mediacontents.view.MediaContentsActivity
import kotlinx.android.synthetic.main.activity_land_loard_profile.*
import kotlinx.android.synthetic.main.item_property_owner_profile.view.*
import kotlin.properties.Delegates


class ProfileFragment : Fragment() {
    companion object {
        fun newInstance() = ProfileFragment()
    }
    private val model: ProfileViewModel by activityViewModels()
    private lateinit var binding: ProfileFragmentBinding
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        sharedPreferencesManager = SharedPreferencesManager(activity)
        ButterKnife.bind(this, binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model.getProfileData(sharedPreferencesManager.getString(SharedPreferencesManager.Key.LOGGED_IN_USERID).toString().trim())!!.observe(
            viewLifecycleOwner,
            Observer { mUsers ->
                updateUI(mUsers?.thumbNail)
                binding.userData = mUsers
                binding.userData!!.addresses = mUsers?.addresses
            })
    }

    fun updateUI(dp: String?) {
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        GlideApp
            .with(this)
            .load(Constants.IMAGE_BASE_URL + dp)
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.photo)
            .into(profile_image)
    }

}
