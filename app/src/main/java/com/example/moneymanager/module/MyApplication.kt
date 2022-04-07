package com.example.moneymanager.module

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
    }
}