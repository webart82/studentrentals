package com.student.rentals.ui.activities

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.student.ApplicationContext
import com.student.rentals.R
import com.student.rentals.ui.AppBaseActivity
import com.student.rentals.ui.fragments.home_fragment.HomeFragment
import com.student.rentals.ui.fragments.list_fragment.ListItemsFragment
import com.student.rentals.ui.fragments.profile_fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as ApplicationContext).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            loadFragment(HomeFragment())
        }
        bottom_navigation.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)
    }

    override fun onStart() {
        super.onStart()
        Timber.d(resources.getString(R.string.on_start))
    }

    override fun onStop() {
        super.onStop()
        Timber.d(resources.getString(R.string.on_stop))
    }

    override fun onRestart() {
        super.onRestart()
        Timber.d(resources.getString(R.string.on_restart))
    }

    override fun onResume() {
        super.onResume()
        Timber.d(resources.getString(R.string.on_resume))
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        menuItem ->
        when (menuItem.itemId){
            R.id.navigation_home -> {
               loadFragment(HomeFragment())
                toobar_add.visibility = View.INVISIBLE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_list -> {
                loadFragment(ListItemsFragment())
                toobar_add.visibility = View.INVISIBLE
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_profile -> {
                loadFragment(ProfileFragment())
                toobar_add.visibility = View.VISIBLE
                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }

    private fun loadFragment(fragment: Fragment) { // load fragment
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
       // transaction.addToBackStack(null)
        transaction.commit()
    }

}
