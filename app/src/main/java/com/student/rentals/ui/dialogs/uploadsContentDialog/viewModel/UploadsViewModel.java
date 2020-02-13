package com.student.rentals.ui.dialogs.uploadsContentDialog.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.student.Api.ApiClient;
import com.student.Api.ApiInterface;
import com.student.models.pApartmentData;

import retrofit2.Call;
import retrofit2.Callback;
import timber.log.Timber;

public class UploadsViewModel extends ViewModel {

    /**
     * this is the data that we will fetch asynchronously
     **/
    private MutableLiveData<pApartmentData> dataList = new MutableLiveData<pApartmentData>();
    ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);


    /**
     * we will call this method to get the data
     **/

    public LiveData<pApartmentData> getApartmentList(String id) {
        //if the list is null

            /**
             * we will load it asynchronously from server in this method
             * **/
            loadDatas(id);


        /**
         * finally we will return the list data's
         */
        return dataList;
    }

    /**
     * This method is using Retrofit to get the JSON data from URL
     **/
    private void loadDatas(String userId) {

        Call<pApartmentData> call = apiInterface.getApartmentsPostedByMe(userId);
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