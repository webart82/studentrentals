package com.student.models

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/1/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
data class DataCookies(
    var originalMaxAge: Int,
    var expires: String?,
    var secure: Boolean?,
    var httpOnly: Boolean?,
    var path: String?
)