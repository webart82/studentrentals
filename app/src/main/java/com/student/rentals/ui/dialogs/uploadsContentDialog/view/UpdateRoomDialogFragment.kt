package com.student.rentals.ui.dialogs.uploadsContentDialog.view

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.student.Utils.Constants
import com.student.Utils.SharedPreferencesManager
import com.student.models.DataRoom
import com.student.rentals.R
import com.student.rentals.databinding.UpdateRoomDataBinding
import com.student.rentals.ui.dialogs.uploadsContentDialog.viewModel.UploadsViewModel
import kotlinx.android.synthetic.main.update_room_data.*
import timber.log.Timber
import java.lang.Integer.parseInt
import java.util.*
import kotlin.concurrent.schedule

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/10/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
class UpdateRoomDialogFragment : DialogFragment() {
    private var content: String? = null
    private var u: DataRoom? = null
    private var OPERATIONAL_ACTION:String? = null
    private var APARTMENT_ID:String? = null
    private val viewModel: UploadsViewModel by activityViewModels()
    private lateinit var binding: UpdateRoomDataBinding
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
        setStyle(style, theme)
        preferencesManager = SharedPreferencesManager(activity)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = UpdateRoomDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (OPERATIONAL_ACTION.equals(Constants.ACTION_EDIT) || OPERATIONAL_ACTION.equals(Constants.ACTION_VIEW)) {
            updateUI(u)
        }

    }

    override fun onResume() {
        super.onResume()

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = LinearLayout.LayoutParams.WRAP_CONTENT
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as android.view.WindowManager.LayoutParams
        if(OPERATIONAL_ACTION.equals(Constants.ACTION_EDIT)) {

            icon_edit.setOnClickListener {
                txtable_layout.isVisible = !txtable_layout.isVisible
                txtedt_layout.isVisible = !txtedt_layout.isVisible
                btn_save.isVisible = !btn_save.isVisible
            }
            btn_cancel.setOnClickListener {
                dismiss()
            }
            btn_save.setOnClickListener {
                editRoomData()
            }
        }else if (OPERATIONAL_ACTION.equals(Constants.ACTION_CREATE)){
            txtedt_layout.visibility = View.VISIBLE
            txtable_layout.visibility =View.INVISIBLE
            icon_edit.visibility = View.INVISIBLE
            btn_save.visibility = View.VISIBLE

            btn_save.setOnClickListener {
                addRoom()
            }
        }else if (OPERATIONAL_ACTION.equals(Constants.ACTION_VIEW)){
            //txtable_layout.visibility =View.VISIBLE
            icon_edit.visibility = View.INVISIBLE
        }
        btn_cancel.setOnClickListener {
            dismiss()
        }

    }

    private fun updateUI(dataRoom: DataRoom?) {
        binding.roomd = dataRoom
    }

    private fun editRoomData() {
        var data = DataRoom(
            edt_name.text.toString().trim(),
            edt_title.text.toString().trim(),
            edt_desc.text.toString().trim(),
            edt_size.text.toString().trim(),
            parseInt(edt_total.text.toString().trim())
        )
        viewModel.updateRoom(data, u?._id)?.observe(viewLifecycleOwner, Observer { RoomData ->
            Timer().schedule(3000) {
                dismiss()
            }

        })
    }
    private fun addRoom(){
        var data = DataRoom(
            edt_name.text.toString().trim(),
            edt_title.text.toString().trim(),
            edt_desc.text.toString().trim(),
            edt_size.text.toString().trim(),
            parseInt(edt_total.text.toString().trim())
        )
        viewModel.createRoom(data, APARTMENT_ID!!)?.observe(viewLifecycleOwner, Observer { RoomData ->
            Timer().schedule(3000) {
                dismiss()
            }

        })
    }

    companion object {

        fun newInstance(content: String, bundle: Bundle, action:String): UpdateRoomDialogFragment {
            val f = UpdateRoomDialogFragment()
            val args = bundle
            var data = bundle.getParcelable<DataRoom>(Constants.PARCEL_KEY)
            args.putString("content", content)
            f.arguments = args
            f.u = data
            f.OPERATIONAL_ACTION = action
            return f
        }
        fun newInstance(apartmentId:String, action:String):UpdateRoomDialogFragment {
            val f = UpdateRoomDialogFragment()
            f.APARTMENT_ID = apartmentId
            f.OPERATIONAL_ACTION = action
            return f
        }
    }

}