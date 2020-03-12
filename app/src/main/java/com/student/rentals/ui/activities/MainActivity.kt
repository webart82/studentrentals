package com.student.rentals.ui.activities

<<<<<<< HEAD
<<<<<<< Updated upstream
=======
import android.content.Intent
>>>>>>> 837c6f0bc54a4f72befbfdc679ef336bdb1e1769
import android.opengl.Visibility
=======
import android.content.Intent
>>>>>>> Stashed changes
import android.os.Bundle
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.student.ApplicationContext
import com.student.rentals.ui.fragments.AddPhotoBottomDialogFragment
import com.student.Utils.BottomNavigationViewBehavior
import com.student.Utils.Constants
import com.student.rentals.R
import com.student.rentals.ui.AppBaseActivity
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
import com.student.rentals.ui.activities.uploadActivity.createApartmentActivity
>>>>>>> 837c6f0bc54a4f72befbfdc679ef336bdb1e1769
import com.student.rentals.ui.fragments.home_fragment.HomeFragment
=======
import com.student.rentals.ui.activities.uploadActivity.CreateNewApartmentActivity
import com.student.rentals.ui.fragments.HomeFragment
>>>>>>> Stashed changes
import com.student.rentals.ui.fragments.list_fragment.ListItemsFragment
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
        val layoutParams = bottom_navigation.getLayoutParams() as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationViewBehavior()
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
<<<<<<< HEAD
<<<<<<< Updated upstream
        Timber.d(resources.getString(R.string.on_resume))
=======
        toobar_add.setOnClickListener{
            startActivity(Intent(this, CreateNewApartmentActivity::class.java))
        }

>>>>>>> Stashed changes
=======
        toobar_add.setOnClickListener{
            startActivity(Intent(this, createApartmentActivity::class.java))
        }
>>>>>>> 837c6f0bc54a4f72befbfdc679ef336bdb1e1769
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
               val fr = AddPhotoBottomDialogFragment.newInstance()
                fr.show(supportFragmentManager,Constants.TAG)
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
