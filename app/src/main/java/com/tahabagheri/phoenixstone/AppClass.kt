package com.tahabagheri.phoenixstone

import android.app.Application
import com.google.firebase.FirebaseApp

class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}