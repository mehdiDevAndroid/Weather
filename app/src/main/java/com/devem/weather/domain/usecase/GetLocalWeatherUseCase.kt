package com.devem.weather.domain.usecase

import android.content.Context
import com.devem.weather.data.local.repository.LocalWeatherRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetLocalWeatherUseCase @Inject constructor(
    @ApplicationContext private val mContext: Context,
    private var repository: LocalWeatherRepository
) {

//    suspend fun execute() : State<List<WeatherView.Data.Timelines.Intervals?>> {
//        return try {
//            val result = repository.getLocalWeather()
//            State.Success(data = result)
//        }catch (e: Exception){
//            State.Error(
//                message = mContext.getString(ErrorTypes.TECHNICAL.message)
//            )
//        }
//    }
}