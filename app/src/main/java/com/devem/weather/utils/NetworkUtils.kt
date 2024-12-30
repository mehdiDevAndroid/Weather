package com.devem.weather.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {
    fun isConnected(mContext: Context) : Boolean {
        val connectivityManager =
            mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}