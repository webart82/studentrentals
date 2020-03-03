package com.student.models

import com.google.gson.annotations.SerializedName

data class DataLoginSuccess(
    @SerializedName("success") var success: Boolean?,
    @SerializedName("status") var status: Int?,
    @SerializedName("uId") var uId: String?,
    @SerializedName("cookies") var cookies: DataCookies?,
    @SerializedName("mail") var mail: String?,
    @SerializedName("accessToken") var accessToken: String?
)