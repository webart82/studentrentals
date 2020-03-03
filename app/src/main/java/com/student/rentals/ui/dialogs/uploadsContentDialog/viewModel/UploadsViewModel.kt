package com.student.rentals.ui.dialogs.uploadsContentDialog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.student.Api.NetworkModule
import com.student.models.DataRoom
import com.student.models.DataApartmentList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class UploadsViewModel : ViewModel() {
    private var dataApartmentListList: MutableLiveData<DataApartmentList?>?= null
    private var dataMutableLiveDataRoom: MutableLiveData<DataRoom?>? = null

    fun getRoomsList(id: String): LiveData<DataApartmentList?> {
        if (dataApartmentListList == null) {
            dataApartmentListList = MutableLiveData()
            loadDatas(id)
        }
        return dataApartmentListList as MutableLiveData<DataApartmentList?>
    }

    fun updateRoom(dataRoom: DataRoom?, roomId: String?): LiveData<DataRoom?>? {
        val call = NetworkModule().apiClient.postToUpdateRoom(dataRoom!!, roomId!!)
        call.enqueue(object : Callback<DataRoom?> {
            override fun onResponse(call: Call<DataRoom?>, response: Response<DataRoom?>) {
                dataMutableLiveDataRoom = MutableLiveData()
                dataMutableLiveDataRoom!!.value = response.body()
            }

            override fun onFailure(call: Call<DataRoom?>, t: Throwable) {
            }
        })
        return dataMutableLiveDataRoom
    }

    private fun loadDatas(iid: String) {
        val call = NetworkModule().apiClient.getApartmentsPostedByMe(iid)
        call.enqueue(object : Callback<DataApartmentList> {
            override fun onResponse(call: Call<DataApartmentList>, response: Response<DataApartmentList>) {
                dataApartmentListList!!.value = response.body()
                Timber.d("HomeView Model" + response.body().toString())
            }

            override fun onFailure(call: Call<DataApartmentList>, t: Throwable) {
            }
        })
    }
}