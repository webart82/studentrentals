package com.student.rentals.ui.activities.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.student.Api.ApiClient;
import com.student.Api.ApiInterface;
import com.student.models.DataLoginSuccess;
import com.student.models.DataSignUp;

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

    public LiveData<DataLoginSuccess> signUpAsNewUser(DataSignUp dataSignUp) {

        /**
         * we will load it asynchronously from server in this method
         * **/
        loadDatas(dataSignUp);
        /**
         * finally we will return the login datas
         */
        return responseList;
    }

    /**
     * This method is using Retrofit to get the JSON data from URL
     **/
    private void loadDatas(DataSignUp dataSignUp) {
        Call<DataLoginSuccess> call = apiInterface.postForSignUp(dataSignUp);
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