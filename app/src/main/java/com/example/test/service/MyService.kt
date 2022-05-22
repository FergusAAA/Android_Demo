package com.example.test.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    private lateinit var mBinder: DownloadBinder

    override fun onBind(intent: Intent): IBinder {
        Log.w(TAG, "onBind: ")
        return mBinder
    }

    override fun onCreate() {
        Log.w(TAG, "onCreate: ")
        super.onCreate()
        mBinder = DownloadBinder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.w(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w(TAG, "onDestroy: ")
    }

    companion object {
        const val TAG = "MyService"
    }

    class DownloadBinder : Binder() {
        fun StartDownload() {
            Log.w(TAG, "StartDownload: ")
        }

        fun getProgress():Int {
            Log.w(TAG, "getProgress: ")
            return 0
        }
    }
}