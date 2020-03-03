package com.student.rentals.ui.fragments.profile_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.student.Api.ApiClient
import com.student.Api.ApiInterface
import com.student.models.DataUser
import com.student.models.DataProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    /**
     * this is the data that we will fetch asynchronously
     */
    private var dataList: MutableLiveData<DataUser?>?= null
    private var dUserData = MutableLiveData<DataUser?>()
    var apiInterface = ApiClient.getInstance().create(
        ApiInterface::class.java
    )


    /**
     * we will call this method to get the data
     */
    fun getProfileData(uId:String): LiveData<DataUser?>? { //if the list is null
        if(dataList == null) {
            dataList = MutableLiveData<DataUser?>()
            loadDatas(uId)
        }
        /**
         * finally we will return the list data's
         */
        return dataList
    }

    fun updateProfile(uId:String, dataProfile: DataProfile):LiveData<DataUser?>?{

        val call = apiInterface.postToUpdateProfile(dataProfile,uId)
        call.enqueue(object : Callback<DataUser>{

            override fun onFailure(call: Call<DataUser>, t: Throwable) {
            }
            override fun onResponse(call: Call<DataUser>, response: Response<DataUser>) {
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
        call.enqueue(object : Callback<DataUser> {
            override fun onResponse(call: Call<DataUser>, response: Response<DataUser>) {
                dataList?.value = response.body()
            }

            override fun onFailure(
                call: Call<DataUser>,
                t: Throwable
            ) {
            }
        })
    }
}
