package com.student.rentals.ui

import com.student.Api.ApiClient
import com.student.Api.ApiInterface

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 3/12/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/

class DataRepository {
    val api = ApiClient.getInstance().create(ApiInterface::class.java)
    suspend fun getApartments() = api.getAllApartments()
}