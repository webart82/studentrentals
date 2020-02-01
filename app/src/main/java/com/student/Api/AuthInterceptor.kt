package com.student.Api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .header("Authorization", "Bearer ")
                .addHeader("Content-Type","application/json")
                .addHeader("Accept", "application/json")

                .build()
        )
    }

}