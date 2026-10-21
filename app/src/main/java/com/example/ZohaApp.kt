package com.example

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp

/**
 * Custom Application class for Zoha Trading.
 * Ensures critical services (Firebase, logging, global crash handler)
 * are initialized safely on startup before any activity or plugin runs,
 * preventing sudden OS force-closes.
 */
class ZohaApp : Application() {

    companion object {
        private const val TAG = "ZohaApp"
        lateinit var instance: ZohaApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        // 1. Global crash handler to catch unhandled errors and log them cleanly
        val previousHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e(TAG, "Uncaught exception on thread [${thread.name}]: ${throwable.message}", throwable)
            previousHandler?.uncaughtException(thread, throwable)
        }

        // 2. Safe early initialization of Firebase
        try {
            FirebaseApp.initializeApp(this)
            Log.d(TAG, "FirebaseApp initialized at Application startup")
        } catch (e: Exception) {
            Log.w(TAG, "Notice: FirebaseApp initialization deferred or error handled: ${e.message}")
        }
    }
}
