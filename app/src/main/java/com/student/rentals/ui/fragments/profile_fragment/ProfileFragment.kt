package com.student.rentals.ui.fragments.profile_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.request.RequestOptions
import com.student.Utils.Constants
import com.student.Utils.GlideApp
import com.student.Utils.SharedPreferencesManager
import com.student.models.dUserData
import com.student.rentals.R
import com.student.rentals.ui.activities.mediacontents.view.MediaContentsActivity
import kotlinx.android.synthetic.main.activity_land_loard_profile.*
import kotlinx.android.synthetic.main.item_property_owner_profile.*
import timber.log.Timber


class ProfileFragment : Fragment() {
    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private val model: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)
        ButterKnife.bind(this,view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.getProfileData(SharedPreferencesManager(activity).getString(SharedPreferencesManager.Key.LOGGED_IN_USERID).toString().trim())!!.observe(viewLifecycleOwner, Observer{ mUsers ->
            updateUI(mUsers!!)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var url = "https://i.pinimg.com/originals/be/ac/96/beac96b8e13d2198fd4bb1d5ef56cdcf.jpg"




    }

    @OnClick(R.id.profile_upload)
    fun uploadImages(){
        startActivity(Intent(context, MediaContentsActivity::class.java))
    }
    fun updateUI(userData: dUserData){
     name.text = userData.fullName
        GlideApp
            .with(this)
            .load(Constants.IMAGE_BASE_URL + userData.thumbNail)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.photo)
            .into(profile_image)
    }

}
