package com.student.rentals.ui.fragments.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.student.Utils.SharedPreferencesManager
import com.student.rentals.R
import com.student.rentals.ui.activities.view.CreateNewApartmentActivity
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import kotlinx.coroutines.runBlocking

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 3/12/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 */
class AddPhotoBottomDialogFragment : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            R.layout.bottom_sheet_layout, container,
            false
        )
    }

    override fun onResume() {
        super.onResume()
        tv_btn_upload_new_apartment.setOnClickListener {
            Intent(context,
                CreateNewApartmentActivity::class.java)
        }
        tv_btn_logout.setOnClickListener{
          runBlocking {
              val sp = com.student.ApplicationContext.instance.getsharepref()
              sp.remove(SharedPreferencesManager.Key.ACCESS_TOKEN_ID)
              sp.remove(SharedPreferencesManager.Key.LOGGED_IN_USERID)
              sp.remove(SharedPreferencesManager.Key.LOGGED_IN_USEREMAIL)
              sp.put(SharedPreferencesManager.Key.IS_USER_LOGGED_IN, false)
          }

        }
    }


    companion object {
        fun newInstance(): AddPhotoBottomDialogFragment {
            return AddPhotoBottomDialogFragment()
        }
    }
}