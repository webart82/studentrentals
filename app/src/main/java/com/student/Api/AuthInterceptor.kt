package com.student.Api

import android.content.Context
import com.student.ApplicationContext
import com.student.Utils.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    private var preferencesManager = SharedPreferencesManager(ApplicationContext.instance)

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .header("Authorization", "Bearer "+preferencesManager.getString(
                    SharedPreferencesManager.Key.ACCESS_TOKEN_ID))
                .addHeader("Content-Type","application/json")
                .addHeader("Accept", "application/json")

                .build()
        )
    }

}