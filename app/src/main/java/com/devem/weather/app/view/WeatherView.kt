package com.devem.weather.app.view

import com.devem.weather.domain.enums.TimeStepTypes


data class WeatherView(
    var data: Data?,
    var warnings: ArrayList<Warnings>?
) {
    data class Warnings(
        var code: Int?,
        var type: String?,
        var message: String?,
        var meta: Meta?
    ){
        data class Meta(
            var timestep: String?,
            var from: String?,
            var to: String?
        )
    }

    data class Data(
        var timelines: ArrayList<Timelines>?
    ) {
        data class Timelines(
            var timestep: TimeStepTypes?,
            var startTime: String?,
            var endTime: String?,
            var intervals: ArrayList<Intervals>
        ) {
            data class Intervals(
                var startTime: String?,
                var values: Values?
            ){
                data class Values(
                    var precipitationIntensity: Double?,
                    var precipitationType: Int?,
                    var windSpeed: Double?,
                    var windGust: Double?,
                    var windDirection: Double?,
                    var temperature: Double?,
                    var temperatureApparent: Double?,
                    var cloudCover: Double?,
                    var cloudBase: String?,
                    var cloudCeiling: String?,
                    var weatherCode: Int?
                )
            }
        }
    }
}