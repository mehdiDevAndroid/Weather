package com.devem.weather.data.json

import com.google.gson.annotations.SerializedName


data class WeatherResponseJson(
    @SerializedName("data")
    var data: Data?,
    @SerializedName("warnings")
    var warnings: ArrayList<Warnings>?
) {
    data class Warnings(
        @SerializedName("code")
        var code: Int?,
        @SerializedName("type")
        var type: String?,
        @SerializedName("message")
        var message: String?,
        @SerializedName("meta")
        var meta: Meta?
    ){
        data class Meta(
            @SerializedName("timestep")
            var timestep: String?,
            @SerializedName("from")
            var from: String?,
            @SerializedName("to")
            var to: String?
        )
    }

    data class Data(
        @SerializedName("timelines")
        var timelines: ArrayList<Timelines>?
    ) {
        data class Timelines(
            @SerializedName("timestep")
            var timestep: String?,
            @SerializedName("startTime")
            var startTime: String?,
            @SerializedName("endTime")
            var endTime: String?,
            @SerializedName("intervals")
            var intervals: ArrayList<Intervals>?
        ) {
            data class Intervals(
                @SerializedName("startTime")
                var startTime: String?,
                @SerializedName("values")
                var values: Values?
            ){
                data class Values(
                    @SerializedName("precipitationIntensity")
                    var precipitationIntensity: Double?,
                    @SerializedName("precipitationType")
                    var precipitationType: Int?,
                    @SerializedName("windSpeed")
                    var windSpeed: Double?,
                    @SerializedName("windGust")
                    var windGust: Double?,
                    @SerializedName("windDirection")
                    var windDirection: Double?,
                    @SerializedName("temperature")
                    var temperature: Double?,
                    @SerializedName("temperatureApparent")
                    var temperatureApparent: Double?,
                    @SerializedName("cloudCover")
                    var cloudCover: Double?,
                    @SerializedName("cloudBase")
                    var cloudBase: String?,
                    @SerializedName("cloudCeiling")
                    var cloudCeiling: String?,
                    @SerializedName("weatherCode")
                    var weatherCode: Int?
                )
            }
        }
    }
}