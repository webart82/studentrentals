package com.student.rentals.ui.activities.LoginActivity.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.student.Models.LoginData;
import com.student.rentals.R;
import com.student.rentals.ui.activities.LoginActivity.ViewModel.LoginViewModel;
import com.student.rentals.ui.activities.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.input_email)
    EditText email;
    @BindView(R.id.input_password)
    EditText password;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getResources().getString(R.string.on_create));
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_login)
        public void login(){
        if (isLogginSuccess(new LoginData(email.getText().toString().trim(), password.getText().toString().trim()))){
            startNewIntent(new Intent(getBaseContext(), MainActivity.class));
        }

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

    private boolean isLogginSuccess(LoginData loginData){
        viewModel.loginWithCredentials(loginData).observe(this, userData -> {

        });
        return true;
    }
    private void startNewIntent(Intent intent){
        startActivity(intent);
    }
}
