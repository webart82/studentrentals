package com.student

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class ApplicationContext : Application() {
    override fun onCreate() {
        super.onCreate()

        // get list of all modules
        val moduleList = listOf(appModule)
        // start koin with the module list
        startKoin(
            this,

            moduleList)

    }

    val appModule = module {

    }

}