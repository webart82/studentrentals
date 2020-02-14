package com.student.rentals.ui.activities.signUp.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.student.Api.ApiClient;
import com.student.Api.ApiInterface;
import com.student.models.LoginData;
import com.student.models.mLoginUserData;
import com.student.rentals.ui.activities.signUp.model.SignUpData;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class SignUpViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";

    @Inject
    public SignUpViewModel(){}

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

    public LiveData<mLoginUserData> signUpAsNewUser(SignUpData signUpData) {

        /**
         * we will load it asynchronously from server in this method
         * **/
        loadDatas(signUpData);
        /**
         * finally we will return the login datas
         */
        return responseList;
    }

    /**
     * This method is using Retrofit to get the JSON data from URL
     **/
    private void loadDatas(SignUpData signUpData) {
        Call<mLoginUserData> call = apiInterface.postForSignUp(signUpData);
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