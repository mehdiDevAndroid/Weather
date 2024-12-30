package com.devem.weather.domain.mapper

import com.devem.weather.app.view.WeatherView
import com.devem.weather.data.json.WeatherResponseJson
import com.devem.weather.domain.enums.TimeStepTypes
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun toTimeLinesList(json: WeatherResponseJson?) =
        ArrayList<WeatherView.Data.Timelines?>().apply {
            json?.data?.timelines?.map { timeline ->
                add(toTimeLines(timeline))
            }
        }

    private fun toTimeLines(json: WeatherResponseJson.Data.Timelines?) = WeatherView.Data.Timelines(
        timestep = TimeStepTypes.fromId(json?.timestep),
        startTime = json?.startTime,
        endTime = json?.endTime,
        intervals = ArrayList(json?.intervals?.map { toInterval(it) } ?: emptyList())
    )


    private fun toInterval(json: WeatherResponseJson.Data.Timelines.Intervals?) =
        WeatherView.Data.Timelines.Intervals(
            startTime = json?.startTime,
            values = toValues(json?.values)
        )

    private fun toValues(json: WeatherResponseJson.Data.Timelines.Intervals.Values?) =
        WeatherView.Data.Timelines.Intervals.Values(
            precipitationIntensity = json?.precipitationIntensity,
            precipitationType = json?.precipitationType,
            windSpeed = json?.windSpeed,
            windGust = json?.windGust,
            windDirection = json?.windDirection,
            temperature = json?.temperature,
            temperatureApparent = json?.temperatureApparent,
            cloudCover = json?.cloudCover,
            cloudBase = json?.cloudBase,
            cloudCeiling = json?.cloudCeiling,
            weatherCode = json?.weatherCode,
        )

}