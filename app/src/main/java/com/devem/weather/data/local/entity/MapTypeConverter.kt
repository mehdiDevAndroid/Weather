package com.devem.weather.data.local.entity

import androidx.room.TypeConverter
import com.google.gson.Gson

object MapTypeConverter {

    @TypeConverter
    @JvmStatic
    fun stringToMap(value: String): ArrayList<String>? {
        return Gson().fromJson(value,  ArrayList<String>()::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(value:  ArrayList<String>?): String {
        return if(value == null) "" else Gson().toJson(value)
    }
}   