package com.student.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TermsDatas(
    @SerializedName("title")val title: String? = null,
    @SerializedName("descriptions")val description: String? = null,
    @SerializedName("_id") var Id:String?
):Parcelable