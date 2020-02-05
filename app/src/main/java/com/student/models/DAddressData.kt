package com.student.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/1/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
@Parcelize
data class DAddressData(
    @SerializedName("_id")
    val Id: String? = null,
    @SerializedName("createdDate")
    val createdDate: String?  = null,
    @SerializedName("lineOne")
    val lineOne: String?  = null,
    @SerializedName("lineTwo")
    val lineTwo: String?   = null,
    @SerializedName("homeNumber")
    val homeNumber: String?  = null,
    @SerializedName("city")
    val city: String?  = null,
    @SerializedName("postalCode")
    val postalCode: String?  = null,
    @SerializedName("state")
    val state: String?  = null,
    @SerializedName("email")
    val email: String?  = null

):Parcelable