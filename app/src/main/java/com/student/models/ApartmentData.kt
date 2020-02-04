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
data class ApartmentData(
    var apartmentType:String?,
    var longitude: String?,
    var latitude: String?,
    var paymentTerms:  String?,
    var amount: String?,
    var description: String?,
    var thumbNail: String?,
    var _id: String?,
    var location: String?,
    var ownersInfo: DUserData?,
    var apartmentName: String?,
    var roomImages:  ArrayList<String>?,
    var rooms: List<mRoomData>?,
    var extraCosts: List<mCostsData>?
):Parcelable