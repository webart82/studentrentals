package com.student.rentals.ui.activities.LoginActivity.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.student.Models.LoginData;
import com.student.Models.UserData;
import com.student.Utils.SharedPreferencesManager;
import com.student.rentals.R;
import com.student.rentals.ui.activities.LoginActivity.ViewModel.LoginViewModel;
import com.student.rentals.ui.activities.MainActivity;
import com.student.rentals.ui.activities.SignUp.view.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private SharedPreferencesManager preferencesManager;
    @BindView(R.id.input_email)
    EditText email;
    @BindView(R.id.input_password)
    EditText password;
     LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getResources().getString(R.string.on_create));
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        preferencesManager = new SharedPreferencesManager(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.link_signup)
    public void signup(){
        startNewIntent(new Intent(getBaseContext(), SignUpActivity.class));
    }

    @OnClick(R.id.btn_login)
        public void login(){
        isLogginSuccess(new LoginData(email.getText().toString().trim(), password.getText().toString().trim()));


    }


    /**
     * Make activity visible to the user
     * Prepare the app to enter foreground and become interactive
     **/
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, getResources().getString(R.string.on_start));
    }

    /**
     * It comes to the foreground
     * The app is now interactive to user
     **/
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, getResources().getString(R.string.on_resume));
    }

    /**
     * User is leaving us
     **/
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, getResources().getString(R.string.on_pause));
    }

    /**
     * Activity is no longer visible to user
     **/
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, getResources().getString(R.string.on_stop));
    }

    /**
     * before activity is destroyed
     **/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, getResources().getString(R.string.on_destroy));
    }

    /**
     * Call this function with @param loginData
     * **/
    private void isLogginSuccess(LoginData loginData){
        viewModel.loginWithCredentials(loginData).observe(this, new Observer<UserData>() {

                /**
                 * Called when the data is changed.
                 *
                 * @param userData The new data
                 */
                @Override
                public void onChanged(UserData userData) {
                    if (userData.getAccessToken()!=null){
                        preferencesManager.put(SharedPreferencesManager.Key.IS_USER_LOGGED_IN, true);
                        preferencesManager.put(SharedPreferencesManager.Key.ACCESS_TOKEN_ID,userData.getAccessToken());

                        startNewIntent(new Intent(getBaseContext(),MainActivity.class));
                    }
                }

        });

    }
    private void startNewIntent(Intent intent){
        startActivity(intent);
    }
}
