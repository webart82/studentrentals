package com.student.rentals.ui.fragments.profile_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.student.Api.ApiClient
import com.student.Api.ApiInterface
import com.student.Utils.SharedPreferencesManager
import com.student.models.dUserData
import com.student.models.mUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ProfileViewModel : ViewModel() {
    /**
     * this is the data that we will fetch asynchronously
     */
    private val dataList = MutableLiveData<dUserData?>()
    var apiInterface = ApiClient.getInstance().create(
        ApiInterface::class.java
    )


    /**
     * we will call this method to get the data
     */
    fun getProfileData(uId:String): LiveData<dUserData?>? { //if the list is null
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
        call.enqueue(object : Callback<dUserData> {
            override fun onResponse(call: Call<dUserData>, response: Response<dUserData>) {
                dataList.value = response.body()
            }

            override fun onFailure(
                call: Call<dUserData>,
                t: Throwable
            ) {
            }
        })
    }
}
