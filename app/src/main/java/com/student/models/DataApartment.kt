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
data class DataApartment(
    @SerializedName("apartmentType") var apartmentType: String? = null,
    @SerializedName("longitude") val longitude: String? = null,
    @SerializedName("latitude") val apartmentLatitude: String? = null,
    @SerializedName("paymentTerms") val apartmentPaymentTerms: String? = null,
    @SerializedName("amount") val apartmentAmount: String? = null,
    @SerializedName("description") val apartmentDescriptions: String? = null,
    @SerializedName("thumbNail") val apartmentThumbnail: String? = null,
    @SerializedName("_id") val apartmentId: String? = null,
    @SerializedName("location") val apartmentLocation: String? = null,
    @SerializedName("ownersInfo") val apartmentOwnersInfo: DataUser? = null,
    @SerializedName("apartmentName") val apartmentName: String? = null,
    @SerializedName("roomImages") val apartmentRoomImages: ArrayList<String>?,
    @SerializedName("rooms") val apartmentRooms: List<DataRoom>?,
    @SerializedName("extraCosts") val apartmentExtraCosts: List<DataExtraCost>
) : Parcelable