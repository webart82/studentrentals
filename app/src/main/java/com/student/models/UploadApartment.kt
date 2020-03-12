package com.student.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 3/12/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
@Parcelize
data class UploadApartment(
    @SerializedName("type") val type: String? = null,
    @SerializedName("longitude") val longitude: String? = null,
    @SerializedName("latitude") val latitude: String? = null,
    @SerializedName("terms") val terms: String? = null,
    @SerializedName("amount") val amount: String? = null,
    @SerializedName("userid") val userid: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("location") val location: String? = null
) : Parcelable