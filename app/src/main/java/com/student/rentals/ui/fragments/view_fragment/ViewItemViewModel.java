package com.student.rentals.ui.fragments.view_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.student.Api.ApiClient;
import com.student.Api.ApiInterface;
import com.student.models.ProfileData;
import com.student.models.dUserData;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 1/28/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
public class ViewItemViewModel extends ViewModel {


    /**
     * this is the data that we will fetch asynchronously
     **/
    private MutableLiveData<dUserData> profileDataMutableLiveData = new MutableLiveData<>();
    ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);

    /**
     * we will call this method to get the data
     **/

    public LiveData<dUserData> getProfileData(String id) {
        //if the list is null
        if (profileDataMutableLiveData == null) {

            /**
             * we will load it asynchronously from server in this method
             * **/
            loadDatas(id);
        }

        /**
         * finally we will return the login datas
         */
        return profileDataMutableLiveData;
    }

    /**
     * This method is using Retrofit to get the JSON data from URL
     **/
    private void loadDatas(String id) {

        Call<dUserData> call = apiInterface.getLoggedInUserProfile(id);
        call.enqueue(new Callback<dUserData>() {
            @Override
            public void onResponse(Call<dUserData> call, retrofit2.Response<dUserData> response) {
                profileDataMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<dUserData> call, Throwable t) {

            }
        });

    }
}
