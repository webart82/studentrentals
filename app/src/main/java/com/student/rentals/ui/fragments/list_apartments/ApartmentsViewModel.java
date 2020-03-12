package com.student.rentals.ui.fragments.list_apartments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.student.Api.NetworkModule;
import com.student.models.DataApartmentList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class ApartmentsViewModel extends ViewModel {
    @Inject public ApartmentsViewModel() { }
    @Inject NetworkModule networkModule;

    private MutableLiveData<DataApartmentList> dataList;

    public LiveData<DataApartmentList> getRoomsList() {
        if (dataList == null) {
            dataList = new MutableLiveData<DataApartmentList>();
            loadDatas();
        }
        return dataList;
    }


    private void loadDatas() {

        Call<DataApartmentList> call = new NetworkModule().getApiClient().getAllApartments();
        call.enqueue(new Callback<DataApartmentList>() {
            @Override
            public void onResponse(Call<DataApartmentList> call, retrofit2.Response<DataApartmentList> response) {
                dataList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DataApartmentList> call, Throwable t) {

            }
        });

    }
}