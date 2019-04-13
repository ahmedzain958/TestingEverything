package com.hogp.testingeverything.koin

import android.app.Application
import org.koin.android.ext.android.startKoin

class KoinDemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule))
    }
}