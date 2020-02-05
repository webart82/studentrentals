package com.student.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/1/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
@Parcelize
data class ExtraCostsData(
    var name:String?,
    var amount: Int?,
    var paymentType: String?,
    var terms: List<mTerms>?

):Parcelable