package com.student.rentals.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.request.RequestOptions
import com.student.Utils.GlideApp
import com.student.rentals.R
import com.student.rentals.ui.AppBaseActivity
import com.student.rentals.ui.fragments.view_fragment.ViewItemFragment
import kotlinx.android.synthetic.main.activity_land_loard_profile.*
import kotlinx.android.synthetic.main.activity_view_property.*

class LandLoadProfileActivity : AppBaseActivity() {

    /**System create the activity {@link onCreate}**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState != null){

        }
        setContentView(R.layout.activity_land_loard_profile)
        //setupToolbar(resources.getString(R.string.profile_toolbar))
    }

    /** Make activity visible to the user
     * Prepare the app to enter foreground and become interactive
     * **/
    override fun onStart() {
        super.onStart()
    }

    /** It comes to the foreground
     * The app is now interactive to user **/
    override fun onResume() {
        super.onResume()
        //setupToolbar()
        var url = "https://i.pinimg.com/originals/be/ac/96/beac96b8e13d2198fd4bb1d5ef56cdcf.jpg"



        GlideApp
            .with(this)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.photo)
            .into(profile_image)
        profile_edit.setOnClickListener {
        }
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
    private fun setupToolbar(tool_bar_tile: String?){
        item_toolbar.title = tool_bar_tile
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
