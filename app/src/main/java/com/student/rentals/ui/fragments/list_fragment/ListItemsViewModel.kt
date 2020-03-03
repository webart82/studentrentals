package com.student.rentals.ui.fragments.list_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.student.Api.ApiClient
import com.student.Api.ApiInterface
import com.student.models.DataUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ListItemsViewModel : ViewModel() {

    private var dataList: MutableLiveData<DataUsers?>? = null
    var apiInterface = ApiClient.getInstance().create(
        ApiInterface::class.java
    )


    /**
     * we will call this method to get the data
     */
    fun getUsersList(): LiveData<DataUsers?>? { //if the list is null
        if(dataList == null){
            dataList = MutableLiveData<DataUsers?>()
            loadDatas()
        }

        /**
         * finally we will return the list data's
         */
        return dataList
    }

    /**
     * This method is using Retrofit to get the JSON data from URL
     */
    private fun loadDatas() {
        val call = apiInterface.getAllUsers()
        call.enqueue(object : Callback<DataUsers> {
            override fun onResponse(
                call: Call<DataUsers>,
                response: Response<DataUsers>
            ) {
                dataList?.value = response.body()
                Timber.d("HomeView Model" + response.body().toString())
            }

            override fun onFailure(
                call: Call<DataUsers>,
                t: Throwable
            ) {
            }
        })
    }
}
