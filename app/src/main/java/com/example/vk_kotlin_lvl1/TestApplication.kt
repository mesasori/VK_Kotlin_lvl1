package com.example.vk_kotlin_lvl1

import android.app.Application
import android.os.StrictMode
import com.nostra13.universalimageloader.BuildConfig

class TestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyDeath()
                    .build()
            )
        }
    }

}