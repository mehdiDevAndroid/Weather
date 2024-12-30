package com.devem.weather.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weather")
class WeatherEntity(
    @PrimaryKey @ColumnInfo(name = "startTime")
    var startTime: String,
    @ColumnInfo(name = "precipitationIntensity")
    var precipitationIntensity: Double?,
    @ColumnInfo(name = "precipitationType")
    var precipitationType: Int?,
    @ColumnInfo(name = "windSpeed")
    var windSpeed: Double?,
    @ColumnInfo(name = "windGust")
    var windGust: Double?,
    @ColumnInfo(name = "windDirection")
    var windDirection: Double?,
    @ColumnInfo(name = "temperature")
    var temperature: Double?,
    @ColumnInfo(name = "temperatureApparent")
    var temperatureApparent: Double?,
    @ColumnInfo(name = "cloudCover")
    var cloudCover: Double?,
    @ColumnInfo(name = "cloudBase")
    var cloudBase: String?,
    @ColumnInfo(name = "cloudCeiling")
    var cloudCeiling: String?,
    @ColumnInfo(name = "weatherCode")
    var weatherCode: Int?
)


