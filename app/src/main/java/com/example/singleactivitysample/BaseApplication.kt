package com.example.singleactivitysample

import android.app.Application
import com.example.singleactivitysample.module.apiModule
import com.example.singleactivitysample.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)

            modules(apiModule)
            modules(viewModelModule)
        }
    }
}