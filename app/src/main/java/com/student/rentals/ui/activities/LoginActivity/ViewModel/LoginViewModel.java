package com.student.rentals.ui.activities.LoginActivity.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.student.Api.ApiClient;
import com.student.Api.ApiInterface;
import com.student.models.LoginData;
import com.student.models.mLoginUserData;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";

    @Inject
    public LoginViewModel(){}
    
    /**
     * this is the data that we will fetch asynchronously
     **/
    private MutableLiveData<mLoginUserData> responseList = new MutableLiveData<mLoginUserData>();
    ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);

    public MutableLiveData<mLoginUserData> getResponseList() {
        return this.responseList;
    }

    public void setResponseList(final MutableLiveData<mLoginUserData> responseList) {
        this.responseList = responseList;
    }


    /**
     * we will call this method to get the data
     **/

    public LiveData<mLoginUserData> loginWithCredentials(LoginData loginData) {

        /**
         * we will load it asynchronously from server in this method
         * **/
        loadDatas(loginData);
        /**
         * finally we will return the login datas
         */
        return responseList;
    }

    /**
     * This method is using Retrofit to get the JSON data from URL
     **/
    private void loadDatas(LoginData loginData) {

        Log.d(TAG, "Try to login with info: " + loginData.toString());
        Call<mLoginUserData> call = apiInterface.postForLogin(loginData);
        call.enqueue(new Callback<mLoginUserData>() {
            @Override
            public void onResponse(Call<mLoginUserData> call, retrofit2.Response<mLoginUserData> response) {
                responseList.setValue(response.body());

            }

            @Override
            public void onFailure(Call<mLoginUserData> call, Throwable t) {

            }
        });

    }

}