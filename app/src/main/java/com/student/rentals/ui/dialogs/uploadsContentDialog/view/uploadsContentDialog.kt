package com.student.rentals.ui.dialogs.uploadsContentDialog.view

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.student.Utils.Constants
import com.student.Utils.SharedPreferencesManager
import com.student.models.ApartmentData
import com.student.models.TermsDatas
import com.student.models.pApartmentData
import com.student.rentals.R
import com.student.rentals.ui.adapters.UploadsListAdapter
import com.student.rentals.ui.dialogs.TermsAndConditionsDialog
import com.student.rentals.ui.dialogs.uploadsContentDialog.viewModel.UploadsViewModel
import kotlinx.android.synthetic.main.uploaded_list.*
import timber.log.Timber

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/10/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
class uploadsContentDialog: DialogFragment() {
    private var content:String? = null
    private var u:List<pApartmentData>? = null
    private val model:UploadsViewModel by activityViewModels()
    private var preferencesManager: SharedPreferencesManager? = null
    override fun getTheme(): Int {
        return R.style.AppTheme_NoActionBar_FullScreenDialog
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d(resources.getString(R.string.on_attach))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d(resources.getString(R.string.on_create))
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogTheme
        setStyle(style,theme)
        preferencesManager = SharedPreferencesManager(activity)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.uploaded_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d(resources.getString(R.string.on_view_created))
    }

    override fun onResume() {
        super.onResume()

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = LinearLayout.LayoutParams.WRAP_CONTENT
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as android.view.WindowManager.LayoutParams

        model.getApartmentList(preferencesManager?.getString(SharedPreferencesManager.Key.LOGGED_IN_USERID))
            .observe(viewLifecycleOwner, Observer{
                updateUI(it.data)
            })
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Timber.d(resources.getString(R.string.on_cancel))
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d(resources.getString(R.string.on_destroy))
    }

    private fun updateUI(data: List<ApartmentData>?){
        Timber.d(data.toString())
        val adapter = UploadsListAdapter(dialog?.context)
        adapter.addItems(data)
        my_uploads_recycler_view.layoutManager = LinearLayoutManager(dialog?.context)
        my_uploads_recycler_view.adapter =  adapter

    }
    companion object{
        fun newInstance(): uploadsContentDialog {
            val f = uploadsContentDialog()
            return f
        }
    }
    
}