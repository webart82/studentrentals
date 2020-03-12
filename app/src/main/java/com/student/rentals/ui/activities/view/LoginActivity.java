package com.student.rentals.ui.activities.view;

import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.student.ApplicationContext;
import com.student.Utils.SharedPreferencesManager.Key;
import com.student.models.DataLogin;
import com.student.models.DataLoginSuccess;
import com.student.Utils.SharedPreferencesManager;
import com.student.rentals.R.id;
import com.student.rentals.R.layout;
import com.student.rentals.ui.activities.viewmodel.LoginViewModel;
import com.student.rentals.ui.activities.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppBaseActivity {
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
        this.finish();
    }

    public Boolean isEmailValid(final String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
