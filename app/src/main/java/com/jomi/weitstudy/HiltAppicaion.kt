package com.jomi.weitstudy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltAppicaion : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}