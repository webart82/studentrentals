package com.student.rentals.ui.activities.uploadActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.student.Api.NetworkModule
import com.student.di.scopes.ApplicationScope
import com.student.models.UploadApartment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 3/11/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 */
@ApplicationScope
public class CreateNewApartmentViewModel: ViewModel(){

    @Inject
    fun CreateNewApartmentViewModel() {}

    private var dataMutableLiveDataRoom: MutableLiveData<UploadApartment?>? = null

    fun createNewApartment(uploadApartment: UploadApartment): LiveData<UploadApartment?>?{
        val call = NetworkModule().apiClient.postToCreateNewApartment(uploadApartment)
        call.enqueue(object : Callback<UploadApartment?> {
            override fun onResponse(call: Call<UploadApartment?>, response: Response<UploadApartment?>) {
                dataMutableLiveDataRoom = MutableLiveData()
            }
            override fun onFailure(call: Call<UploadApartment?>, t: Throwable) {
            }
        })
        return dataMutableLiveDataRoom
    }
}