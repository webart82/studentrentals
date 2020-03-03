package com.student.rentals.ui.activities.loginActivity.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.student.Api.ApiClient;
import com.student.Api.ApiInterface;
import com.student.di.scopes.ApplicationScope;
import com.student.models.DataLogin;
import com.student.models.DataLoginSuccess;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

@ApplicationScope
public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";


    @Inject
    public LoginViewModel(){}
    
    /**
     * this is the data that we will fetch asynchronously
     **/
    private MutableLiveData<DataLoginSuccess> responseList = new MutableLiveData<DataLoginSuccess>();
    ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);

    public MutableLiveData<DataLoginSuccess> getResponseList() {
        return this.responseList;
    }

    public void setResponseList(final MutableLiveData<DataLoginSuccess> responseList) {
        this.responseList = responseList;
    }


    /**
     * we will call this method to get the data
     **/

    public LiveData<DataLoginSuccess> loginWithCredentials(DataLogin datalogin) {

        /**
         * we will load it asynchronously from server in this method
         * **/
        loadDatas(datalogin);
        /**
         * finally we will return the login datas
         */
        return responseList;
    }

    /**
     * This method is using Retrofit to get the JSON data from URL
     **/
    private void loadDatas(DataLogin datalogin) {

        Log.d(TAG, "Try to login with info: " + datalogin.toString());
        Call<DataLoginSuccess> call = apiInterface.postForLogin(datalogin);
        call.enqueue(new Callback<DataLoginSuccess>() {
            @Override
            public void onResponse(Call<DataLoginSuccess> call, retrofit2.Response<DataLoginSuccess> response) {
                responseList.setValue(response.body());

            }

            @Override
            public void onFailure(Call<DataLoginSuccess> call, Throwable t) {

            }
        });

    }

}