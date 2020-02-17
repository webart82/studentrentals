package com.student.rentals.ui.dialogs.uploadsContentDialog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.student.Api.NetworkModule
import com.student.models.RoomData
import com.student.models.pApartmentData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class UploadsViewModel : ViewModel() {
    private var dataList: MutableLiveData<pApartmentData?>?= null
    private var roomDataMutableLiveData: MutableLiveData<RoomData?>? = null

    fun getRoomsList(id: String): LiveData<pApartmentData?> {
        if (dataList == null) {
            dataList = MutableLiveData()
            loadDatas(id)
        }
        return dataList as MutableLiveData<pApartmentData?>
    }

    fun updateRoom(roomData: RoomData?, roomId: String?): LiveData<RoomData?>? {
        val call = NetworkModule().apiClient.postToUpdateRoom(roomData!!, roomId!!)
        call.enqueue(object : Callback<RoomData?> {
            override fun onResponse(call: Call<RoomData?>, response: Response<RoomData?>) {
                roomDataMutableLiveData = MutableLiveData()
                roomDataMutableLiveData!!.value = response.body()
            }

            override fun onFailure(call: Call<RoomData?>, t: Throwable) {
            }
        })
        return roomDataMutableLiveData
    }

    private fun loadDatas(iid: String) {
        val call = NetworkModule().apiClient.getApartmentsPostedByMe(iid)
        call.enqueue(object : Callback<pApartmentData> {
            override fun onResponse(call: Call<pApartmentData>, response: Response<pApartmentData>) {
                dataList!!.value = response.body()
                Timber.d("HomeView Model" + response.body().toString())
            }

            override fun onFailure(call: Call<pApartmentData>, t: Throwable) {
            }
        })
    }
}