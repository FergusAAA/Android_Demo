package com.example.test.service

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService : IntentService("MyIntentService") {
    /**
     * 在子线程中调用
     * 每一次调起服务都会调用该方法
     */
    override fun onHandleIntent(intent: Intent?) {
        for (i in 1..60) {
            Thread.sleep(100)
        }
        Log.w(TAG, "onHandleIntent: ThreadName: " + Thread.currentThread().name)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w(TAG, "onDestroy: ")
    }

    companion object {
        private const val TAG = "MyIntentService"
    }
}