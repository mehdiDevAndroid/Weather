package com.devem.weather.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devem.weather.data.local.dao.WeatherDao
import com.devem.weather.data.local.entity.MapTypeConverter
import com.devem.weather.data.local.entity.WeatherEntity


@Database(
    entities = [
        WeatherEntity::class],
    version = 31,
    exportSchema = false
)
@TypeConverters(MapTypeConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun userDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                                context.applicationContext,
                                WeatherDatabase::class.java,
                                "weather_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}