package com.awesomeproject

import android.util.Log
import android.content.Intent
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.facebook.react.HeadlessJsTaskService;
import android.os.Bundle;

class SaveLocationBackgroundModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName() = "SaveLocationBackgroundModule"

    // Declare reactContext as a member variable
    private val myReactContext = reactContext

    @ReactMethod
    fun startReactNativeLocationSampling() {
       val serviceIntent = Intent(myReactContext, ReactNativeLocationService::class.java)
       myReactContext.startService(serviceIntent)
    }

    @ReactMethod
    fun stopReactNativeLocationSampling() {
       val serviceIntent = Intent(myReactContext, ReactNativeLocationService::class.java)
       myReactContext.stopService(serviceIntent)
    }

    @ReactMethod
    fun startAndroidLocationSampling() {
        val serviceIntent = Intent(myReactContext, AndroidLocationService::class.java)
        myReactContext.startService(serviceIntent)
    }

    @ReactMethod
    fun stopAndroidLocationSampling() {
        val serviceIntent = Intent(myReactContext, AndroidLocationService::class.java)
        myReactContext.stopService(serviceIntent)
    }
}
