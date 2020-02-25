package com.student.rentals.ui.dialogs.uploadsContentDialog.view

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
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
import com.student.models.ExtraCostsData
import com.student.models.RoomData
import com.student.rentals.R
import com.student.rentals.databinding.ExtraCostsDataBinding
import com.student.rentals.databinding.UpdateRoomDataBinding
import com.student.rentals.ui.dialogs.uploadsContentDialog.viewModel.CoastsViewModel
import kotlinx.android.synthetic.main.extra_costs_data.*
import kotlinx.android.synthetic.main.update_room_data.*
import kotlinx.android.synthetic.main.update_room_data.btn_cancel
import kotlinx.android.synthetic.main.update_room_data.btn_save
import kotlinx.android.synthetic.main.update_room_data.edt_name
import kotlinx.android.synthetic.main.update_room_data.icon_edit
import kotlinx.android.synthetic.main.update_room_data.txtedt_layout
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
class CostsDialogFragment : DialogFragment() {
    private var content: String? = null
    private var du: String? = null
    private val viewModel: CoastsViewModel by activityViewModels()
    private lateinit var binding: ExtraCostsDataBinding
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ExtraCostsDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d(resources.getString(R.string.on_view_created))
        //updateUI(u)
        //Timber.d(u.toString())

    }

    override fun onResume() {
        super.onResume()

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = LinearLayout.LayoutParams.WRAP_CONTENT
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as android.view.WindowManager.LayoutParams

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

    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Timber.d(resources.getString(R.string.on_cancel))
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d(resources.getString(R.string.on_destroy))
    }

    private fun updateUI(extraCostsData: ExtraCostsData?) {
        binding.extradatas = extraCostsData
    }

    private fun editRoomData() {
        var data = ExtraCostsData(
            edt_name.text.toString().trim(),
            edt_amount.text.toString().trim(),
            edt_payment_type.text.toString().trim()
        )
        viewModel.addNewCost(data, du)?.observe(viewLifecycleOwner, Observer {
            Timer().schedule(3000) {
                dismiss()
                Timber.d(it.toString())
            }

        })
    }

    companion object {

        fun newInstance(content: String): CostsDialogFragment {
            val f = CostsDialogFragment()
            //val args = bundle
            //var data = bundle.getEx(Constants.PARCEL_KEY)
            //args.putString("content", content)
            //f.arguments = args
            f.du = content


            return f
        }
    }

}