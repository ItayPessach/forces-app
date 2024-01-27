package com.awesomeproject

import android.content.pm.PackageManager
import android.os.Looper
import android.location.Location
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.Arguments

class FusedLocationModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(reactContext)

    private var lastKnownLocation: Location? = null

    init {
        // Start location updates when the module is created
        startLocationUpdates()
    }

    override fun getName() = "FusedLocationModule"

      private fun createLocationData(location: Location): WritableMap {
         val locationData = Arguments.createMap()
         locationData.putDouble("latitude", location.latitude)
         locationData.putDouble("longitude", location.longitude)

         return locationData
      }

    @ReactMethod
    fun getLastLocation(promise: Promise) {
        /*fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    promise.resolve(createLocationData(location))
                } else {
                    promise.reject("LOCATION_NULL", "Last known location is null")
                }
            }
            .addOnFailureListener { e ->
                promise.reject("LOCATION_ERROR", e.message ?: "Failed to get last known location")
            }*/
             if (lastKnownLocation != null) {
                  val lastLocation = lastKnownLocation!!
                  promise.resolve(createLocationData(lastLocation))
             } else {
                  promise.reject("LOCATION_NULL", "Last known location is null")
             }
    }

       private fun startLocationUpdates() {
                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult?) {
                        super.onLocationResult(locationResult)
                        locationResult?.lastLocation?.let {
                            lastKnownLocation = it
                        }
                    }
                }

                val locationRequest = LocationRequest.create()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(5000) // 5 seconds interval

                 fusedLocationClient.requestLocationUpdates(
                     locationRequest,
                     locationCallback,
                     Looper.getMainLooper()
                 )
         }
}
