package com.student.Api

import androidx.lifecycle.LiveData
import com.student.models.RoomData
import com.student.models.LoginData
import com.student.models.ProfileData
import com.student.models.mLoginUserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("users")
    fun getUsers(): LiveData<List<mLoginUserData>>

    @GET("users/{id}")
    fun getUser(): Call<String>

    @GET( "apartment/list")
    fun getAllRooms(): Call<List<RoomData>>

    @GET("/apartment/users/{id}")
    fun getLoggedInUserProfile(@Path("id") userId:Int): Call<ProfileData>

    @POST("auth/login")
    fun postForLogin(@Body loginData: LoginData):Call<mLoginUserData>


}
