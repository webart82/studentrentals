package com.student

import android.app.Application
import com.student.di.ApplicationComponent
import com.student.di.DaggerApplicationComponent
import com.student.rentals.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree


class ApplicationContext : Application() {
    lateinit var appComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

    }
    companion object {
        lateinit var instance: ApplicationContext
            private set
    }
}