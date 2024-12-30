package com.devem.weather.utils

import android.util.Log

object Extensions {

    fun Any.logInfo(message: String){
        Log.i(this::class.java.simpleName, message)
    }

    fun Any.logError(message: String){
        Log.e(this::class.java.simpleName, message)
    }
}