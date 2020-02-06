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
data class ExtraCostsData(
    @SerializedName("name") val name: String? = null,
    @SerializedName("amount") val amount: String? = null,
    @SerializedName("paymentType") val paymentType: String? = null,
    @SerializedName("termsAndConditions") var terms: List<mTerms>?=null

) : Parcelable