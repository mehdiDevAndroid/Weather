package com.devem.weather.domain.usecase

import com.devem.weather.app.view.WeatherView
import com.devem.weather.data.repository.Repository
import com.devem.weather.domain.enums.TimeStepTypes
import com.devem.weather.domain.mapper.Mapper
import com.devem.weather.domain.retrofit.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherUseCase @Inject constructor() {

    @Inject
    lateinit var repository: Repository
    @Inject
    lateinit var mapper: Mapper

    suspend fun execute(
    ): Flow<State<ArrayList<WeatherView.Data.Timelines?>>> = flow {
        repository.getWeather().collect {
            val result = it.mapTo(
                dataConverter = { response ->
                    ArrayList(
                        mapper.toTimeLinesList(response).filter { timeLine ->
                            timeLine?.timestep == TimeStepTypes.HOUR
                        }
                    )
                }
            )
            emit(result)
        }
    }
}