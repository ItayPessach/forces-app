package com.awesomeproject

import android.util.Log
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.facebook.react.HeadlessJsTaskService
import android.os.Bundle;
import androidx.core.app.NotificationCompat

class ReactNativeLocationService : Service() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate() {
        super.onCreate()

        // Initialize handler and runnable
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                val serviceIntent = Intent(getApplicationContext(), SaveReactNativeLocation::class.java)
                val bundle = Bundle()

                bundle.putString("foo", "bar")
                serviceIntent.putExtras(bundle)
                getApplicationContext().startService(serviceIntent)
                HeadlessJsTaskService.acquireWakeLockNow(getApplicationContext());

                // Schedule the task to run again after 5 seconds
                handler.postDelayed(this, 5000)
            }
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("ReactNativeLocationService", "react native location service started")
        // Start the task when the service is started
        handler.post(runnable)

        start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("ReactNativeLocationService", "react native location service stopped")
        super.onDestroy()
        // Stop the task when the service is destroyed
        handler.removeCallbacks(runnable)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun start() {
        val notification = NotificationCompat.Builder(
            this,
            "save_location_channel"
        )
         .setContentTitle("Location Sampling")
         .setContentText("sampling location every 5 minutes")
         .build()

        startForeground(1, notification)
    }
}
