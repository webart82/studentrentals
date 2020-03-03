package com.student.rentals.ui.dialogs.uploadsContentDialog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.student.Api.NetworkModule
import com.student.models.DataExtraCost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoastsViewModel : ViewModel() {
    private var dataList: MutableLiveData<DataExtraCost?>?= null


    fun addNewCost(dataextracost: DataExtraCost?, apartmentId: String?): LiveData<DataExtraCost?>? {
        val call = NetworkModule().apiClient.postToCreateNewCost(dataextracost!!, apartmentId!!)
        call.enqueue(object : Callback<DataExtraCost?> {
            override fun onResponse(call: Call<DataExtraCost?>, response: Response<DataExtraCost?>) {
                dataList = MutableLiveData()
              dataList!!.value = response.body()
            }

            override fun onFailure(call: Call<DataExtraCost?>, t: Throwable) {
            }
        })
        return dataList
    }

}