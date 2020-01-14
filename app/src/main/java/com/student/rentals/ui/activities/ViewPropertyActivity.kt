package com.student.rentals.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.student.rentals.R
import com.student.rentals.ui.fragments.view_fragment.ViewItemFragment
import kotlinx.android.synthetic.main.activity_view_property.*

class ViewPropertyActivity : AppCompatActivity() {

    /**System create the activity {@link onCreate}**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState != null){

        }
        setContentView(R.layout.activity_view_property)
    }

    /** Make activity visible to the user
     * Prepare the app to enter foreground and become interactive
     * **/
    override fun onStart() {
        loadFragment(ViewItemFragment())
        super.onStart()
    }

    /** It comes to the foreground
     * The app is now interactive to user **/
    override fun onResume() {
        setupToolbar()
        super.onResume()
    }

    /** User is leaving us **/
    override fun onPause() {
        super.onPause()
    }

    /**Activity is no longer visible to user**/
    override fun onStop() {
        super.onStop()
    }

    /** before activity is destroyed **/
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)
    }
    private fun loadFragment(fragment: Fragment) { // load fragment
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container_view_item, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun setupToolbar(){
        item_toolbar.title = getString(R.string.app_name)
        setSupportActionBar(item_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }

}
