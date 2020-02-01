package com.student.models

data class mLoginUserData(
    var success: Boolean?,
    var status: Int?,
    var uId: String?,
    var cookies: mCookies?,
    var mail: String?,
    var accessToken: String?
)