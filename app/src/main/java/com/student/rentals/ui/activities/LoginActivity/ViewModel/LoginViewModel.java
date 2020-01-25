package com.student.rentals.ui.activities.LoginActivity.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.student.Api.ApiClient;
import com.student.Api.ApiInterface;
import com.student.Models.LoginData;
import com.student.Models.UserData;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";

    /**
     * this is the data that we will fetch asynchronously
     **/
    private MutableLiveData<UserData> responseList = new MutableLiveData<UserData>();
    ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);

    public MutableLiveData<UserData> getResponseList() {
        return this.responseList;
    }

    public void setResponseList(final MutableLiveData<UserData> responseList) {
        this.responseList = responseList;
    }


    /**
     * we will call this method to get the data
     **/

    public LiveData<UserData> loginWithCredentials(LoginData loginData) {

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
        Call<UserData> call = apiInterface.postForLogin(loginData);
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, retrofit2.Response<UserData> response) {
                responseList.setValue(response.body());
                Log.d(TAG, response.toString());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });

    }

}