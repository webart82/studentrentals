package com.student.rentals.ui.fragments.profile_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.request.RequestOptions
import com.student.Utils.GlideApp
import com.student.rentals.R
import com.student.rentals.ui.activities.mediacontents.view.MediaContentsActivity
import kotlinx.android.synthetic.main.item_property_owner_profile.*


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)
        ButterKnife.bind(this,view)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        var url = "https://i.pinimg.com/originals/be/ac/96/beac96b8e13d2198fd4bb1d5ef56cdcf.jpg"



        GlideApp
            .with(this)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.photo)
            .into(profile_image)
    }

    @OnClick(R.id.profile_upload)
    fun uploadImages(){
        startActivity(Intent(context, MediaContentsActivity::class.java))
    }

}
