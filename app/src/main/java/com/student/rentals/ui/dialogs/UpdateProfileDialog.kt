package com.student.rentals.ui.dialogs

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.student.Utils.SharedPreferencesManager
import com.student.models.DataProfile
import com.student.rentals.R
import com.student.rentals.ui.fragments.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_update_profile.*
import timber.log.Timber
import java.util.*
import kotlin.concurrent.schedule


/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/8/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
class UpdateProfileDialog : DialogFragment() {
    private val viewModel: ProfileViewModel by activityViewModels()
    private var preferencesManager: SharedPreferencesManager? = null
    override fun getTheme(): Int {
        return R.style.AppTheme_NoActionBar_FullScreenDialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogTheme
        setStyle(style, theme)
        preferencesManager = SharedPreferencesManager(context)
        retainInstance = true
    }

    override fun onResume() {
        super.onResume()

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height = LinearLayout.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params as android.view.WindowManager.LayoutParams

        edit_cancelbtn.setOnClickListener {
            dismiss()
        }
        btn_close.setOnClickListener {
            dismiss()
        }
        edit_okbtn.setOnClickListener() {
            updateProfile()
            p_progress_bar.visibility = View.VISIBLE
            txt_alert.visibility = View.VISIBLE
            edit_okbtn.isEnabled = false
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d(resources.getString(R.string.on_attach))
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Timber.d("DiALOG DISMISSED")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_profile, container, false)
        //updateUI(u)

        return view
    }

    companion object {
        fun newInstance(): UpdateProfileDialog {
            val f = UpdateProfileDialog()
            return f
        }
    }

    private fun updateProfile() {
        val username = edit_username.text.toString().trim()
        val lastname = edit_lastname.text.toString().trim()
        val firstname = edit_firstname.text.toString().trim()
        val email = edit_email.text.toString().trim()
        val about = edit_about.text.toString().trim()
        val title = edit_title.text.toString().trim()
        val jobtitle = edit_jobtitle.text.toString().trim()
        val userdata = DataProfile(username, firstname, lastname, email, about, title, jobtitle)
        viewModel.updateProfile(
            preferencesManager!!.getString(SharedPreferencesManager.Key.LOGGED_IN_USERID),
            userdata
        )!!
            .observe(viewLifecycleOwner, Observer { DUserData ->
                Timer().schedule(3000) {
                    dismiss()
                }
            })
    }
}