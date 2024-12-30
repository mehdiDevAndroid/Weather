package com.devem.weather.app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devem.weather.app.view.WeatherView
import com.devem.weather.domain.retrofit.State
import com.devem.weather.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var getWeatherUseCase: GetWeatherUseCase

    private val _weatherLD = MutableLiveData<State<ArrayList<WeatherView.Data.Timelines?>>>()
    val weatherLD: LiveData<State<ArrayList<WeatherView.Data.Timelines?>>> = _weatherLD

    private var getWeatherJOB: Job? = null

    fun getWeather() {
        getWeatherJOB?.cancel()
        getWeatherJOB = viewModelScope.launch(Dispatchers.IO) {
            try {
                _weatherLD.postValue(State.Loading())
                getWeatherUseCase.execute().collect {
                    _weatherLD.postValue(it)
                }
            } catch (exception: Exception) {
                _weatherLD.postValue(State.Loading())
                handleCancellableJobFailure(
                    logMessage = "Get Weather Failure",
                    exception = exception,
                    onFailure = {
                        _weatherLD.postValue(State.Error(data = null, message = exception.message))
                    },
                )
            }
        }
    }
}