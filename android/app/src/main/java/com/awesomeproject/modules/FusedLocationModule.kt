package com.awesomeproject

import android.location.Location
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.Arguments

class FusedLocationModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(reactContext)

    override fun getName() = "FusedLocationModule"

      private fun createLocationData(location: Location): WritableMap {
         val locationData = Arguments.createMap()
         locationData.putDouble("latitude", location.latitude)
         locationData.putDouble("longitude", location.longitude)

         return locationData
      }

    @ReactMethod
    fun getLastLocation(promise: Promise) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    promise.resolve(createLocationData(location))
                } else {
                    promise.reject("LOCATION_NULL", "Last known location is null")
                }
            }
            .addOnFailureListener { e ->
                promise.reject("LOCATION_ERROR", e.message ?: "Failed to get last known location")
            }
    }
}
