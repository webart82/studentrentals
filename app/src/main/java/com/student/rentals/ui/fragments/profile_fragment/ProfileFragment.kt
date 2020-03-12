package com.student.rentals.ui.fragments.profile_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import butterknife.ButterKnife
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.student.ApplicationContext
import com.student.Utils.Constants
import com.student.Utils.GlideApp
import com.student.Utils.SharedPreferencesManager
import com.student.rentals.R
import com.student.rentals.databinding.ProfileFragmentBinding
import com.student.rentals.ui.adapters.ProfilePagerAdapter
import com.student.rentals.ui.dialogs.UpdateProfileDialog
import com.student.rentals.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.activity_land_loard_profile.*


class ProfileFragment : BaseFragment() {
    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val model: ProfileViewModel by activityViewModels()
    private lateinit var binding: ProfileFragmentBinding
    private lateinit var profilePagerAdapter: ProfilePagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        ButterKnife.bind(this, binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profilePagerAdapter =
            ProfilePagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager = view.findViewById(R.id.profile_view_pager)
        viewPager.adapter = profilePagerAdapter
        tab_layout.setupWithViewPager(viewPager)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        fetchData()
        profile_edit.setOnClickListener() {
            val ft: FragmentTransaction = (activity as AppCompatActivity?)!!.supportFragmentManager.beginTransaction()
            val nInstance = UpdateProfileDialog.newInstance()
            nInstance.show(ft, "UPDATE_PROFILE")
        }

        /**
         * profile_upload.setOnClickListener() {
            val ft: FragmentTransaction =
                (activity as AppCompatActivity?)!!.supportFragmentManager.beginTransaction()
            val nInstance = UpdateProfileDialog.newInstance()
            nInstance.show(ft, "UPDATE_PROFILE")
        }**/

    }


    private fun updateUI(dp: String?) {
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        GlideApp
            .with(this)
            .load(Constants.IMAGE_BASE_URL + dp)
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.person)
            .error((R.drawable.person))
            .into(profile_image)
    }


    private fun fetchData() {
        p_profile_layout.visibility = View.INVISIBLE
        model.getProfileData(
            ApplicationContext.instance.getsharepref().getString(
                SharedPreferencesManager.Key.LOGGED_IN_USERID
            ).toString().trim()
        )!!.observe(
            viewLifecycleOwner,
            Observer { mUsers ->
                p_profile_layout.visibility = View.VISIBLE
                pprogress_bar.visibility = View.INVISIBLE
                updateUI(mUsers?.thumbNail)
                binding.userData = mUsers
                binding.addressData = mUsers?.addresses
            })
    }
}
