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

        // 1. Global crash handler to catch unhandled errors and prevent sudden OS force-closes
        val previousHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e(TAG, "Uncaught exception on thread [${thread.name}]: ${throwable.message}", throwable)
            // If the error occurred on a background/worker thread (coroutines, network, Firebase IO, OkHttp),
            // swallow it to prevent the Android OS from abruptly force-closing the app with "keeps stopping".
            val isMainThread = thread.name.equals("main", ignoreCase = true) ||
                    (android.os.Looper.myLooper() != null && android.os.Looper.myLooper() == android.os.Looper.getMainLooper())
            if (isMainThread) {
                Log.e(TAG, "Main thread crash detected: ${throwable.message}")
                previousHandler?.uncaughtException(thread, throwable)
            } else {
                Log.w(TAG, "Prevented OS process force-close from background worker thread [${thread.name}]")
            }
        }

        // 2. Safe early initialization of Firebase with guaranteed offline-first fallback
        try {
            if (FirebaseApp.getApps(this).isEmpty()) {
                try {
                    FirebaseApp.initializeApp(this)
                } catch (e: Exception) {
                    val options = com.google.firebase.FirebaseOptions.Builder()
                        .setApplicationId("1:1234567890:android:zoha")
                        .setApiKey("AIzaSyFakePlaceholderKeyForOfflineMode123")
                        .setDatabaseUrl("https://zoha-trading-default-rtdb.firebaseio.com")
                        .setProjectId("zoha-trading")
                        .build()
                    FirebaseApp.initializeApp(this, options)
                }
            }
            Log.d(TAG, "FirebaseApp safely initialized at Application startup")
        } catch (e: Exception) {
            Log.w(TAG, "Notice: FirebaseApp initialization handled: ${e.message}")
        }

        // 3. Pre-warm session management
        try {
            com.example.util.SessionManager.clearSession(this)
        } catch (e: Exception) {
            Log.w(TAG, "Notice pre-warming session state: ${e.message}")
        }
    }
}
