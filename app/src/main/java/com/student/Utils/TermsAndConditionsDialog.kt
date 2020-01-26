package com.student.Utils

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.ViewParent
import android.view.Window
import android.view.WindowManager
import com.student.rentals.R

class TermsAndConditionsDialog(context: Context):AlertDialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.terms_and_condition)
        val window = this.window
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}