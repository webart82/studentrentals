package com.student

import android.app.Application
import com.student.di.applicationModule
import com.student.rentals.BuildConfig
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class ApplicationContext : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        startKoin(

            this,

            listOf(applicationModule))

    }




}