package com.example.viewpagerdemo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

class ViewPagerDemoApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        getToken()
    }

    private fun getToken() {
        val result = BufferedReader(InputStreamReader(assets.open("token"))).readText()
    }
}