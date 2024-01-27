package com.awesomeproject

import android.util.Log
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.facebook.react.HeadlessJsTaskService
import android.os.Bundle;

class AndroidLocationService : Service() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate() {
        super.onCreate()

        // Initialize handler and runnable
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                val serviceIntent = Intent(getApplicationContext(), SaveAndroidLocation::class.java)
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
        Log.d("AndroidLocationService", "android location service started")
        // Start the task when the service is started
        handler.post(runnable)
        return START_STICKY
    }

    override fun onDestroy() {
        Log.d("AndroidLocationService", "android location service stopped")
        super.onDestroy()
        // Stop the task when the service is destroyed
        handler.removeCallbacks(runnable)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
