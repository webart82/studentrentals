package com.student.rentals.ui.fragments.home_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.student.Api.ApiClient;
import com.student.Api.ApiInterface;
import com.student.Models.RoomData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeViewModel extends ViewModel {

    public MutableLiveData<List<RoomData>> getDataList() {
        return this.dataList;
    }

    public void setDataList(final MutableLiveData<List<RoomData>> dataList) {
        this.dataList = dataList;
    }

    /**
     * this is the data that we will fetch asynchronously
     **/
    private MutableLiveData<List<RoomData>> dataList = new MutableLiveData<List<RoomData>>();
    ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);


    /**
     * we will call this method to get the data
     **/

    public LiveData<List<RoomData>> getRoomsList() {
        //if the list is null
        if (dataList == null) {

            /**
             * we will load it asynchronously from server in this method
             * **/
            loadDatas();
        }

        /**
         * finally we will return the login datas
         */
        return dataList;
    }

    /**
     * This method is using Retrofit to get the JSON data from URL
     **/
    private void loadDatas() {

        Call<List<RoomData>> call = apiInterface.getAllRooms();
        call.enqueue(new Callback<List<RoomData>>() {
            @Override
            public void onResponse(Call<List<RoomData>> call, retrofit2.Response<List<RoomData>> response) {
                dataList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<RoomData>> call, Throwable t) {

            }
        });

    }
}