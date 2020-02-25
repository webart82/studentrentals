package com.student.rentals.ui.dialogs.uploadsContentDialog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.student.Api.NetworkModule
import com.student.models.ExtraCostsData
import com.student.models.RoomData
import com.student.models.pApartmentData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class CoastsViewModel : ViewModel() {
    private var dataList: MutableLiveData<ExtraCostsData?>?= null


    fun addNewCost(extraCostsData: ExtraCostsData?, apartmentId: String?): LiveData<ExtraCostsData?>? {
        val call = NetworkModule().apiClient.postToCreateNewCost(extraCostsData!!, apartmentId!!)
        call.enqueue(object : Callback<ExtraCostsData?> {
            override fun onResponse(call: Call<ExtraCostsData?>, response: Response<ExtraCostsData?>) {
                dataList = MutableLiveData()
              dataList!!.value = response.body()
            }

            override fun onFailure(call: Call<ExtraCostsData?>, t: Throwable) {
            }
        })
        return dataList
    }

}