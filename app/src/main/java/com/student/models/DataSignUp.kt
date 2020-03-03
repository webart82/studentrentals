package com.student.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataSignUp(
    @SerializedName("email")val email: String?=null,
    @SerializedName("password")val password: String? = null
):Parcelable