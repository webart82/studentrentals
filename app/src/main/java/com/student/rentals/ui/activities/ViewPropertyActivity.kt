package com.student.rentals.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.student.Utils.Constants
import com.student.Utils.GlideApp
import com.student.models.ApartmentData
import com.student.rentals.R
import com.student.rentals.databinding.ActivityViewPropertyBinding
import kotlinx.android.synthetic.main.activity_view_property.*
import kotlinx.android.synthetic.main.fragment_view_item.*


class ViewPropertyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewPropertyBinding

    /**System create the activity {@link onCreate}**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_property)
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

        // loadFragment(ViewItemFragment())
        val budle = intent.getBundleExtra(Constants.PARCEL_BUNDLE)
        val obj = budle.getParcelable<ApartmentData>(Constants.PARCEL_KEY)
        val rooms = obj?.rooms
        val images = obj?.roomImages
        val costs = obj?.extraCosts
        

        binding.productData = obj
        obj?.thumbNail?.let { updateUI(it) }
        obj?.apartmentName?.let { setupToolbar(it) }
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

    private fun setupToolbar(str: String) {
        item_toolbar.title = str
        setSupportActionBar(item_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }

    private fun updateUI(url: String) {
        GlideApp
            .with(this)
            .load(url)
            .transition(
                DrawableTransitionOptions.withCrossFade(
                    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(
                        true
                    ).build()
                )
            )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)

            .into(property_image)
    }

}
