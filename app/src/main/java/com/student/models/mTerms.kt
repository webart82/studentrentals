package com.student.models

import android.accounts.AuthenticatorDescription
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/1/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
@Parcelize
data class mTerms(
    var title:String?,
    var description: String?,
    var _id: String?
):Parcelable