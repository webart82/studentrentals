package com.student.rentals.ui

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.student.rentals.R

open class AppBaseActivity: AppCompatActivity(){
   val  TAG = "Baseactivity"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Log.d(TAG,resources.getString(R.string.on_create))
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
        Log.d(TAG, resources.getString(R.string.on_create_view))
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, resources.getString(R.string.on_start))
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, resources.getString(R.string.on_stop))
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, resources.getString(R.string.on_resume))
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,resources.getString(R.string.on_restart))
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,resources.getString(R.string.on_destroy))
    }

}