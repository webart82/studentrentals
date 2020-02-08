package com.student.rentals.ui.activities.signUp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignUpData(
    @SerializedName("email")val email: String?=null,
    @SerializedName("password")val password: String? = null
):Parcelable