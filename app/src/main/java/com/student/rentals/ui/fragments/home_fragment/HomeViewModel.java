package com.student.rentals.ui.fragments.home_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.student.Api.ApiClient;
import com.student.Api.ApiInterface;
import com.student.models.mApartmentData;
import com.student.models.pApartmentData;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import timber.log.Timber;

public class HomeViewModel extends ViewModel {

    @Inject
    public HomeViewModel(){}
    /**
     * this is the data that we will fetch asynchronously
     **/
    private MutableLiveData<pApartmentData> dataList = new MutableLiveData<pApartmentData>();
    ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);


    /**
     * we will call this method to get the data
     **/

    public LiveData<pApartmentData> getRoomsList() {
        //if the list is null

            /**
             * we will load it asynchronously from server in this method
             * **/
            loadDatas();


        /**
         * finally we will return the list data's
         */
        return dataList;
    }

    /**
     * This method is using Retrofit to get the JSON data from URL
     **/
    private void loadDatas() {

        Call<pApartmentData> call = apiInterface.getAllApartments();
        call.enqueue(new Callback<pApartmentData>() {
            @Override
            public void onResponse(Call<pApartmentData> call, retrofit2.Response<pApartmentData> response) {
                dataList.setValue(response.body());
                Timber.d("HomeView Model"+response.body().toString());
            }

            @Override
            public void onFailure(Call<pApartmentData> call, Throwable t) {

            }
        });

    }
}