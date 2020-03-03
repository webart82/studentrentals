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
data class DataUser(
    @SerializedName("address")
    val addresses: DataAddress? = null,
    @SerializedName("_id")
    val Id: String?,
    @SerializedName("createdDate")
    val createdDate: String? = null,
    @SerializedName("userName")
    val userName: String? = null,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("thumbNail")
    val thumbNail: String?=null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("fullName")
    val fullName: String? = null,
    @SerializedName("jobTitle")
    val jobTitle: String?= null,
    @SerializedName("about")
    val about: String? = null
    ):Parcelable