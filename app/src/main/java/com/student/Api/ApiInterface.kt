package com.student.Api

import androidx.lifecycle.LiveData
import com.student.models.*
import com.student.rentals.ui.activities.signUp.model.SignUpData
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("users")
    fun getUsers(): LiveData<List<mLoginUserData>>

    @GET( "apartments/all")
    fun getAllApartments(): Call<pApartmentData>

    @GET("users")
    fun getAllUsers():Call<mUsers>

    @GET("users/{id}")
    fun getLoggedInUserProfile(@Path("id") userId:String): Call<DUserData>

    @GET("apartments/{id}/me")
    fun getApartmentsPostedByMe(@Path("id") userId:String): Call<pApartmentData>

    @POST("auth/login")
    fun postForLogin(@Body loginData: LoginData):Call<mLoginUserData>

    @POST("/apartments/{id}/rooms")
    fun postToCreateNewRoom(@Body roomData: RoomData, @Path("id") apartmentId: String):Call<RoomData>

    @POST("auth/")
    fun postForSignUp(@Body signUpData: SignUpData):Call<mLoginUserData>

    @PATCH("users/{id}")
    fun postToUpdateProfile(@Body userProfileData: UserProfileData, @Path("id") userId: String):Call<DUserData>

    @PATCH("rooms/{id}")
    fun postToUpdateRoom(@Body roomData: RoomData, @Path("id") roomId: String):Call<RoomData>
}
