package com.student.Utils

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.student.models.DUserData
import com.student.rentals.R
import com.student.rentals.databinding.ItemPropertyPopupProfileBinding
import kotlinx.android.synthetic.main.item_property_popup_profile.*
import timber.log.Timber

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 1/28/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 */
class CustomDialogFragment : DialogFragment() {
    private var content:String? = null
    private var u:DUserData? = null
    private lateinit var binding: ItemPropertyPopupProfileBinding
    override fun getTheme(): Int {
        return R.style.AppTheme_NoActionBar_FullScreenDialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = arguments?.getString("content")
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogTheme
        setStyle(style,theme)
        retainInstance = true
    }
    override fun onResume() {
        super.onResume()

        val params:ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = LinearLayout.LayoutParams.WRAP_CONTENT
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as android.view.WindowManager.LayoutParams

        popup_close.setOnClickListener {
            Toast.makeText(activity, "Action cancelled", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d(resources.getString(R.string.on_attach))
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ItemPropertyPopupProfileBinding.inflate(inflater, container, false)
        updateUI(u)

        return binding.root
    }

   companion object{
       fun newInstance(content: String, bundle:Bundle): CustomDialogFragment{
           val f = CustomDialogFragment()
           val args = bundle
           var data = bundle.getParcelable<DUserData>(Constants.PARCEL_KEY)

           args.putString("content", content)
           f.arguments = args
           f.u = data
           return f
       }
   }
   open fun updateUI( u:DUserData?) {

        binding.userData = u
       Timber.d(u.toString())
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
       GlideApp
            .with(dialog?.context!!)
            .load(Constants.IMAGE_BASE_URL + u?.thumbNail)
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.person)
            .error((R.drawable.person))
            .into(binding.root.findViewById(R.id.popup_profile_image))


    }
}