package com.student

import android.app.Application
import com.student.Utils.SharedPreferencesManager
import com.student.di.ApplicationComponent
import com.student.di.DaggerApplicationComponent
import com.student.rentals.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree


class ApplicationContext : Application() {
    lateinit var appComponent: ApplicationComponent
    lateinit var preferencesManager: SharedPreferencesManager
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
        preferencesManager = SharedPreferencesManager(this)
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

    }

    fun getsharepref(): SharedPreferencesManager {
        return preferencesManager
    }

    companion object {
        lateinit var instance: ApplicationContext
            private set
    }
}