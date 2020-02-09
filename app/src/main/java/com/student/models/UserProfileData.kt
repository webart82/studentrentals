package com.student.models

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 1/28/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/

data class UserProfileData(
    val username:String?=null,
    val firstname:String?=null,
    val lastname:String?=null,
    val email:String?=null,
    val about:String?=null,
    val title:String?=null,
    val jobtitle:String?=null
)