package com.devem.weather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devem.weather.data.local.entity.WeatherEntity


@Dao
interface WeatherDao {

    @Query("SELECT * from weather")
    fun getWeathers(): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(user: WeatherEntity) : Long

}