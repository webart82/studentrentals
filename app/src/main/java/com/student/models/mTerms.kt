package com.student.models

import android.accounts.AuthenticatorDescription
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
data class mTerms(
    @SerializedName("title")
    val title:String? = null,
    @SerializedName("descriptions")
    val description: String? = null,
    @SerializedName("_id")
    var Id: String?= null
):Parcelable