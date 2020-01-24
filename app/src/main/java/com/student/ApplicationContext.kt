package com.student

import android.app.Application
import com.student.di.applicationModule
import org.koin.android.ext.android.startKoin

class ApplicationContext : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(

            this,

            listOf(applicationModule))

    }




}