package com.student.Api

import androidx.lifecycle.LiveData
import com.student.models.*
import com.student.rentals.ui.activities.signUp.model.SignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("users")
    fun getUsers(): LiveData<List<mLoginUserData>>

    @GET( "apartments/all")
    fun getAllApartments(): Call<pApartmentData>

    @GET("users")
    fun getAllUsers():Call<mUsers>

    @GET("users/{id}")
    fun getLoggedInUserProfile(@Path("id") userId:String): Call<DUserData>

    @POST("auth/login")
    fun postForLogin(@Body loginData: LoginData):Call<mLoginUserData>

    @POST("auth/")
    fun postForSignUp(@Body signUpData: SignUpData):Call<mLoginUserData>


}
