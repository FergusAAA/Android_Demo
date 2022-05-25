package com.example.test.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.test.R
import com.example.test.view.ServiceTest

class ForegroundService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        Log.w(TAG, "onCreate: ")
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val channel = NotificationChannel("ForegroundService", "前台Service", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, ServiceTest::class.java)
        val activity = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, "ForegroundService")
                .setContentTitle("this is content title")
                .setContentText("this is content text")
                .setSmallIcon(R.drawable.abc_vector_test)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.abc_vector_test))
                .setContentIntent(activity)
                .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.w(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.w(TAG, "onDestroy: ")
        super.onDestroy()
    }

    companion object {
        private const val TAG = "FrontDeskService"
    }
}