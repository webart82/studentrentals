package com.student.rentals.ui.fragments.home_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.student.Api.ApiClient;
import com.student.Api.ApiInterface;
import com.student.Api.NetworkModule;
import com.student.models.mApartmentData;
import com.student.models.pApartmentData;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import timber.log.Timber;

public class HomeViewModel extends ViewModel {
    @Inject public HomeViewModel() { }
    @Inject NetworkModule networkModule;

    private MutableLiveData<pApartmentData> dataList;

    public LiveData<pApartmentData> getRoomsList() {
        if (dataList == null) {
            dataList = new MutableLiveData<pApartmentData>();
            loadDatas();
        }
        return dataList;
    }


    private void loadDatas() {

        Call<pApartmentData> call = new NetworkModule().getApiClient().getAllApartments();
        call.enqueue(new Callback<pApartmentData>() {
            @Override
            public void onResponse(Call<pApartmentData> call, retrofit2.Response<pApartmentData> response) {
                dataList.setValue(response.body());
                Timber.d("HomeView Model" + response.body().toString());
            }

            @Override
            public void onFailure(Call<pApartmentData> call, Throwable t) {

            }
        });

    }
}