package com.example.moveeapp.application

import android.app.Application
import android.content.Context

class MoveeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
    }

    companion object {

        lateinit var appContext: Context

    }
}
