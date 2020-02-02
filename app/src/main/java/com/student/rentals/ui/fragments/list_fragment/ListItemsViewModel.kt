package com.student.rentals.ui.fragments.list_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.student.Api.ApiClient
import com.student.Api.ApiInterface
import com.student.models.mUsers
import com.student.models.pApartmentData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ListItemsViewModel : ViewModel() {
    /**
     * this is the data that we will fetch asynchronously
     */
    private val dataList = MutableLiveData<mUsers?>()
    var apiInterface = ApiClient.getInstance().create(
        ApiInterface::class.java
    )


    /**
     * we will call this method to get the data
     */
    fun getUsersList(): LiveData<mUsers?>? { //if the list is null
        /**
         * we will load it asynchronously from server in this method
         */
        loadDatas()
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
        call.enqueue(object : Callback<mUsers> {
            override fun onResponse(
                call: Call<mUsers>,
                response: Response<mUsers>
            ) {
                dataList.value = response.body()
                Timber.d("HomeView Model" + response.body().toString())
            }

            override fun onFailure(
                call: Call<mUsers>,
                t: Throwable
            ) {
            }
        })
    }
}
