package com.student.rentals.ui.fragments.profile_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.student.Api.ApiClient
import com.student.Api.ApiInterface
import com.student.models.DUserData
import com.student.models.UserProfileData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    /**
     * this is the data that we will fetch asynchronously
     */
    private var dataList: MutableLiveData<DUserData?>?= null
    private var dUserData = MutableLiveData<DUserData?>()
    var apiInterface = ApiClient.getInstance().create(
        ApiInterface::class.java
    )


    /**
     * we will call this method to get the data
     */
    fun getProfileData(uId:String): LiveData<DUserData?>? { //if the list is null
        if(dataList == null) {
            dataList = MutableLiveData<DUserData?>()
            loadDatas(uId)
        }
        /**
         * finally we will return the list data's
         */
        return dataList
    }

    fun updateProfile(uId:String, userProfileData: UserProfileData):LiveData<DUserData?>?{

        val call = apiInterface.postToUpdateProfile(userProfileData,uId)
        call.enqueue(object : Callback<DUserData>{

            override fun onFailure(call: Call<DUserData>, t: Throwable) {
            }
            override fun onResponse(call: Call<DUserData>, response: Response<DUserData>) {
                dUserData.value = response.body()
            }

        })
        return dUserData
    }

    /**
     * This method is using Retrofit to get the JSON data from URL
     */
    private fun loadDatas(userId: String) {
        val call = apiInterface.getLoggedInUserProfile(userId)
        call.enqueue(object : Callback<DUserData> {
            override fun onResponse(call: Call<DUserData>, response: Response<DUserData>) {
                dataList?.value = response.body()
            }

            override fun onFailure(
                call: Call<DUserData>,
                t: Throwable
            ) {
            }
        })
    }
}
