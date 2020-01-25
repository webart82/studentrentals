package com.student.Api

import androidx.lifecycle.LiveData
import com.student.Models.RoomData
import com.student.Models.LoginData
import com.student.Models.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("users")
    fun getUsers(): LiveData<List<UserData>>

    @GET("users/{id}")
    fun getUser(): Call<String>

    @GET( "apartment/list")
    fun getAllRooms(): Call<List<RoomData>>

    @POST("")
    fun postForLogin(@Body loginData: LoginData):Call<UserData>


}
