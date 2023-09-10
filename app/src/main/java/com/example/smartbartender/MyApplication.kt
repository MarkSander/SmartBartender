package com.example.smartbartender

import android.app.Application

class MyApplication : Application() {
    lateinit var appPreferences: AppPreferences

    override fun onCreate() {
        super.onCreate()
        appPreferences = AppPreferences(applicationContext)
    }
}