package com.student.rentals.ui.activities.LoginActivity.ViewModel;

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
        //if the list is null
        if (responseList == null) {

            /**
             * we will load it asynchronously from server in this method
             * **/
            loadDatas(loginData);
        }

        /**
         * finally we will return the login datas
         */
        return responseList;
    }



    /**
     * This method is using Retrofit to get the JSON data from URL
     **/
    private void loadDatas(LoginData loginData) {

        Call<UserData> call = apiInterface.postForLogin(loginData);
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, retrofit2.Response<UserData> response) {
                responseList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });

    }

}