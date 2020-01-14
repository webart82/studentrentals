package com.student.rentals.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.ListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.student.rentals.R
import com.student.rentals.ui.AppBaseActivity
import com.student.rentals.ui.fragments.home_fragment.HomeFragment
import com.student.rentals.ui.fragments.list_fragment.ListItemsFragment
import com.student.rentals.ui.fragments.profile_fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null){
            loadFragment(HomeFragment())
        }
        bottom_navigation.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        menuItem ->
        when (menuItem.itemId){
            R.id.navigation_home -> {
               loadFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_list -> {
                loadFragment(ListItemsFragment())
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_profile -> {
                loadFragment(ProfileFragment())
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
