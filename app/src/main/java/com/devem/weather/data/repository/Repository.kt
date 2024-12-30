package com.devem.weather.data.repository

import com.devem.weather.data.json.WeatherResponseJson
import com.devem.weather.data.service.Service
import com.devem.weather.domain.retrofit.BaseDataSource
import com.devem.weather.domain.retrofit.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor() {

    @Inject
    lateinit var service: Service

    @Inject
    lateinit var baseDataSource: BaseDataSource

    suspend fun getWeather(): Flow<State<WeatherResponseJson>> = flow {
        val response = baseDataSource.getResult {
            service.getWeather()
        }
        emit(response)
    }
}


