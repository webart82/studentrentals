package com.student.rentals.ui.activities.view

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.student.rentals.R
import timber.log.Timber

open class AppBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Timber.d(resources.getString(R.string.on_create))
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        Timber.d(resources.getString(R.string.on_create_view))
        return super.onCreateView(name, context, attrs)
    }


    /**
     * Make activity visible to the user
     * Prepare the app to enter foreground and become interactive
     */
    override fun onStart() {
        Timber.d(resources.getString(R.string.on_start))
        super.onStart()
    }


    /**
     * It comes to the foreground
     * The app is now interactive to user
     */
    override fun onResume() {
        Timber.d(this.resources.getString(R.string.on_resume))
        super.onResume()
    }

    /**
     * User is leaving us
     */
    override fun onPause() {
        Timber.d(this.resources.getString(R.string.on_pause))
        super.onPause()
    }

    /**
     * Activity is no longer visible to user
     */
    override fun onStop() {
        Timber.d(this.resources.getString(R.string.on_stop))
        super.onStop()
    }

    /**
     * before activity is destroyed
     */
    override fun onDestroy() {
        Timber.d(this.resources.getString(R.string.on_destroy))
        super.onDestroy()
    }


    /**
     * activity restarted after stopped for sometimes**/
    override fun onRestart() {
        Timber.d(resources.getString(R.string.on_restart))
        super.onRestart()
    }


}