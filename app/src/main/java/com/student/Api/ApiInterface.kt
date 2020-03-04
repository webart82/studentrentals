package com.student.Api

import androidx.lifecycle.LiveData
import com.student.models.*
import com.student.models.DataSignUp
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("users")
    fun getUsers(): LiveData<List<DataLoginSuccess>>

    @GET( "apartments/all")
    fun getAllApartments(): Call<DataApartmentList>

    @GET("users")
    fun getAllUsers():Call<DataUsers>

    @GET("users/{id}")
    fun getLoggedInUserProfile(@Path("id") userId:String): Call<DataUser>

    @GET("apartments/{id}/me")
    fun getApartmentsPostedByMe(@Path("id") userId:String): Call<DataApartmentList>

    @POST("auth/login")
    fun postForLogin(@Body datalogin: DataLogin):Call<DataLoginSuccess>

    @POST("apartments/{id}/costs")
    fun postToCreateNewCost(@Body dataextracost: DataExtraCost, @Path("id") apartmentId: String):Call<DataExtraCost>

    @POST("apartments/{id}/rooms")
    fun postToCreateNewRoom(@Body dataRoom: DataRoom, @Path("id") apartmentId: String):Call<DataRoom>

    @POST("auth/")
    fun postForSignUp(@Body dataSignUp: DataSignUp):Call<DataLoginSuccess>

    @PATCH("users/{id}")
    fun postToUpdateProfile(@Body dataProfile: DataProfile, @Path("id") userId: String):Call<DataUser>

    @PATCH("rooms/{id}")
    fun postToUpdateRoom(@Body dataRoom: DataRoom, @Path("id") roomId: String):Call<DataRoom>
}
