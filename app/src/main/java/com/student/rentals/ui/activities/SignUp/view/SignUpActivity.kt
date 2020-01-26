package com.student.rentals.ui.activities.SignUp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.student.rentals.R
import com.student.rentals.ui.activities.LoginActivity.View.LoginActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        link_login.setOnClickListener(){
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
    /** Make activity visible to the user
     * Prepare the app to enter foreground and become interactive
     * **/
    override fun onStart() {
        //loadFragment(ViewItemFragment())
        super.onStart()
    }

    /** It comes to the foreground
     * The app is now interactive to user **/
    override fun onResume() {

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
}