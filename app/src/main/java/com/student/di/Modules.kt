package com.student.di

import com.student.Api.AuthInterceptor
import com.student.ApplicationContext
import org.koin.dsl.module.module

val applicationModule = module(override = true) {

    factory { AuthInterceptor() }
    /**

    factory { provideOkHttpClient(get()) }
    factory { provideApi(get()) }
    single { provideRetrofit(get()) }

     **/
}