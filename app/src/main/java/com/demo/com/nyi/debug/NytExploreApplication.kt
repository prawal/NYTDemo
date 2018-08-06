package com.demo.com.nyi.debug

import android.app.Application

import com.facebook.stetho.Stetho

/**
 * This subclass of Application is solely used to initialise Stetho (Chrome-based debugging tool).
 */
class NytExploreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}
