package com.example.medicalscanner

import android.app.Application
import com.example.medicalscanner.network.NetworkModule

import com.example.medicalscanner.network.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@MyApp)
            androidFileProperties()
            koin.loadModules(
                listOf(vmModule, NetworkModule.create()))
        }
    }
}