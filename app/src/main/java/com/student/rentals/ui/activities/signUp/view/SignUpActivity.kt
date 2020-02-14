package com.student.rentals.ui.activities.signUp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.student.ApplicationContext
import com.student.Utils.SharedPreferencesManager
import com.student.rentals.R
import com.student.rentals.ui.activities.LoginActivity.View.LoginActivity
import com.student.rentals.ui.activities.LoginActivity.ViewModel.LoginViewModel
import com.student.rentals.ui.activities.MainActivity
import com.student.rentals.ui.activities.signUp.ViewModel.SignUpViewModel
import com.student.rentals.ui.activities.signUp.model.SignUpData
import kotlinx.android.synthetic.main.activity_sign_up.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class SignUpActivity : AppCompatActivity() {

    private var preferencesManager: SharedPreferencesManager? = null


    var viewModel: SignUpViewModel? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as ApplicationContext).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        Timber.d(resources.getString(R.string.on_create))
        setContentView(R.layout.activity_sign_up)
        preferencesManager = SharedPreferencesManager(this)
    }


    /** Make activity visible to the user
     * Prepare the app to enter foreground and become interactive
     * **/
    override fun onStart() {
        //loadFragment(ViewItemFragment())
        super.onStart()
        Timber.d(resources.getString(R.string.on_start))
    }

    /** It comes to the foreground
     * The app is now interactive to user **/
    override fun onResume() {
        super.onResume()
        Timber.d(resources.getString(R.string.on_resume))
        btn_signup.setOnClickListener() {
            val email = input_email.text.toString().trim()
            val password = input_password.text.toString().trim()
            val cpassword = input_confirm_password.text.toString().trim()
            if (!isEmailValid(email)) input_email.setError("Invalid Email address !!!")
            //btn_signup.isEnabled = false
            if (isValidPassword(password, cpassword) && password.length >= 8)
                input_confirm_password.setError("Invalid password")
            if (isValidPassword(password, cpassword) && isEmailValid(email)
            ) {
                SignUp(SignUpData(email, password))
            }
        }
        link_login.setOnClickListener() {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    /** User is leaving us **/
    override fun onPause() {
        super.onPause()

        Timber.d(resources.getString(R.string.on_pause))
    }

    /**Activity is no longer visible to user**/
    override fun onStop() {
        super.onStop()
        Timber.d(resources.getString(R.string.on_stop))
    }

    /** before activity is destroyed **/
    override fun onDestroy() {
        super.onDestroy()
        Timber.d(resources.getString(R.string.on_destroy))
    }

    /** Validate inputs fun **/
    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String, repass: String): Boolean {
        return password.equals(repass)
    }

    private fun SignUp(signUpData: SignUpData) {
        p_progress_bar.visibility = View.VISIBLE
        sign_up_alert.visibility = View.VISIBLE
        viewModel?.signUpAsNewUser(signUpData)?.observe(this, Observer { mLoginUserData ->
            Timber.d(mLoginUserData.toString())
            if (mLoginUserData.accessToken != null) {
                sign_up_alert.text = "Signup successful"
                preferencesManager?.put(
                    SharedPreferencesManager.Key.ACCESS_TOKEN_ID,
                    mLoginUserData.accessToken
                )
                preferencesManager?.put(
                    SharedPreferencesManager.Key.LOGGED_IN_USERID,
                    mLoginUserData.uId
                )
                preferencesManager?.put(
                    SharedPreferencesManager.Key.LOGGED_IN_USEREMAIL,
                    mLoginUserData.mail
                )
                preferencesManager?.put(SharedPreferencesManager.Key.IS_USER_LOGGED_IN, true)

            }
            Timer().schedule(5000) {
                startLoginIntent()
            }


        })
    }

    private fun startLoginIntent() {

        startActivity(Intent(baseContext, MainActivity::class.java))
        this.finish()
    }

}