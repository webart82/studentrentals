package com.student.rentals.ui.fragments.profile_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.student.Api.ApiClient
import com.student.Api.ApiInterface
import com.student.models.DUserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    /**
     * this is the data that we will fetch asynchronously
     */
    private val dataList = MutableLiveData<DUserData?>()
    var apiInterface = ApiClient.getInstance().create(
        ApiInterface::class.java
    )


    /**
     * we will call this method to get the data
     */
    fun getProfileData(uId:String): LiveData<DUserData?>? { //if the list is null
        /**
         * we will load it asynchronously from server in this method
         */
        loadDatas(uId)
        /**
         * finally we will return the list data's
         */
        return dataList
    }

    /**
     * This method is using Retrofit to get the JSON data from URL
     */
    private fun loadDatas(userId: String) {
        val call = apiInterface.getLoggedInUserProfile(userId)
        call.enqueue(object : Callback<DUserData> {
            override fun onResponse(call: Call<DUserData>, response: Response<DUserData>) {
                dataList.value = response.body()
            }

            override fun onFailure(
                call: Call<DUserData>,
                t: Throwable
            ) {
            }
        })
    }
}
