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
data class DAddressData(
    var _id: String?,
    var createdDate: String?,
    var lineOne: String?,
    var lineTwo: String?,
    var homeNumber: String?,
    var city: String?,
    var postalCode: String?,
    var state: String?,
    var email: String?

):Parcelable