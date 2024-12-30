package com.devem.weather.data.service

import com.devem.weather.data.json.WeatherResponseJson
import retrofit2.Response
import retrofit2.http.GET


interface Service {

    @GET("weather/")
    suspend fun getWeather(): Response<WeatherResponseJson>

}