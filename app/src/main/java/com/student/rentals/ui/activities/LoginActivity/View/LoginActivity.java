package com.student.rentals.ui.activities.LoginActivity.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.student.ApplicationContext;
import com.student.Utils.SharedPreferencesManager.Key;
import com.student.models.DataLogin;
import com.student.models.DataLoginSuccess;
import com.student.Utils.SharedPreferencesManager;
import com.student.rentals.R.id;
import com.student.rentals.R.layout;
import com.student.rentals.R.string;
import com.student.rentals.ui.activities.LoginActivity.ViewModel.LoginViewModel;
import com.student.rentals.ui.activities.MainActivity;
import com.student.rentals.ui.activities.signUp.view.SignUpActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private SharedPreferencesManager preferencesManager;
    @BindView(id.input_email)
    EditText emailadress;
    @BindView(id.input_password)
    EditText password;
    @Inject
    LoginViewModel viewModel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        ((ApplicationContext)getApplicationContext()).appComponent.inject(this);
        super.onCreate(savedInstanceState);
        Log.d(LoginActivity.TAG, this.getResources().getString(string.on_create));
        this.preferencesManager = new SharedPreferencesManager(this);
        this.setContentView(layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(id.link_signup)
    public void signup() {
        this.startNewIntent(new Intent(this.getBaseContext(), SignUpActivity.class));
    }

    @OnClick(id.btn_login)
    public void login() {
        final String email = this.emailadress.getText().toString().trim();
        final String passwrd = this.password.getText().toString().trim();

        if ( !email.isEmpty() && !passwrd.isEmpty() && email != null && passwrd != null && this.isEmailValid(email)) {
            this.isLogginSuccess(new DataLogin(email, passwrd));
        }else {
            this.password.setError("Invalid Email or Password !!!");
        }


    }


    /**
     * Make activity visible to the user
     * Prepare the app to enter foreground and become interactive
     **/
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LoginActivity.TAG, this.getResources().getString(string.on_start));
    }

    /**
     * It comes to the foreground
     * The app is now interactive to user
     **/
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LoginActivity.TAG, this.getResources().getString(string.on_resume));
    }

    /**
     * User is leaving us
     **/
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LoginActivity.TAG, this.getResources().getString(string.on_pause));
    }

    /**
     * Activity is no longer visible to user
     **/
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LoginActivity.TAG, this.getResources().getString(string.on_stop));
    }

    /**
     * before activity is destroyed
     **/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LoginActivity.TAG, this.getResources().getString(string.on_destroy));
    }

    /**
     * Call this function with @param loginData
     **/
    private void isLogginSuccess(final DataLogin datalogin) {
        this.viewModel.loginWithCredentials(datalogin).observe(this, new Observer<DataLoginSuccess>() {

            /**
             * Called when the data is changed.
             *
             * @param DataLoginSuccess The new data
             */
            @Override
            public void onChanged(final DataLoginSuccess DataLoginSuccess) {
                if (DataLoginSuccess.getAccessToken() != null || !! DataLoginSuccess.getAccessToken().isEmpty()) {
                    LoginActivity.this.preferencesManager.put(Key.ACCESS_TOKEN_ID, DataLoginSuccess.getAccessToken());
                    LoginActivity.this.preferencesManager.put(Key.LOGGED_IN_USERID, DataLoginSuccess.getUId());
                    LoginActivity.this.preferencesManager.put(Key.LOGGED_IN_USEREMAIL, DataLoginSuccess.getMail());
                    LoginActivity.this.preferencesManager.put(Key.IS_USER_LOGGED_IN, true);
                    LoginActivity.this.startNewIntent(new Intent(LoginActivity.this.getBaseContext(), MainActivity.class));
                } else {

                }
            }


        });

    }

    private void startNewIntent(final Intent intent) {
        this.startActivity(intent);
        finish();
    }

    public Boolean isEmailValid(final String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
