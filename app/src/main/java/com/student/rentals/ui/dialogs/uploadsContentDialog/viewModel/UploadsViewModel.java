package com.student.rentals.ui.dialogs.uploadsContentDialog.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.student.Api.ApiClient;
import com.student.Api.ApiInterface;
import com.student.Api.NetworkModule;
import com.student.models.RoomData;
import com.student.models.pApartmentData;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class UploadsViewModel extends ViewModel {
    @Inject public UploadsViewModel() { }
    @Inject NetworkModule networkModule;

    private MutableLiveData<pApartmentData> dataList;
    private MutableLiveData<RoomData> roomDataMutableLiveData;

    public LiveData<pApartmentData> getRoomsList( String id) {
        if (dataList == null) {
            dataList = new MutableLiveData<pApartmentData>();
            loadDatas(id);
        }
        return dataList;
    }
    public LiveData<RoomData> updateRoom(RoomData roomData, String roomId){
        Call<RoomData> call = new NetworkModule().getApiClient().postToUpdateRoom(roomData, roomId);
        call.enqueue(new Callback<RoomData>() {
            @Override
            public void onResponse(Call<RoomData> call, Response<RoomData> response) {
                roomDataMutableLiveData = new MutableLiveData<RoomData>();
                roomDataMutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<RoomData> call, Throwable t) {

            }
        });
        return roomDataMutableLiveData;
    }


    private void loadDatas(String iid) {
        Call<pApartmentData> call = new NetworkModule().getApiClient().getApartmentsPostedByMe(iid);
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