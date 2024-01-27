package com.awesomeproject

import android.util.Log
import android.content.Context
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import java.io.*

class FileStorageModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName() = "FileStorageModule"

     @ReactMethod
     fun saveDataToFile(data: String, file: String) {
       Log.d("FileStorageModule", "append location to a file")
          try {
            val fileOutputStream = reactApplicationContext.openFileOutput(file, Context.MODE_APPEND)
            val writer = BufferedWriter(OutputStreamWriter(fileOutputStream))
            writer.append(data)
            writer.close()
          } catch (e: IOException) {
              e.printStackTrace()
           }
        }
}
