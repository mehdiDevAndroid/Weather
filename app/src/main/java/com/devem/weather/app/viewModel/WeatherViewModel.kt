package com.devem.weather.app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devem.weather.app.view.WeatherView
import com.devem.weather.domain.retrofit.State
import com.devem.weather.domain.usecase.GetLocalWeatherUseCase
import com.devem.weather.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
) : BaseViewModel() {


    @Inject
    lateinit var getWeatherUseCase: GetWeatherUseCase

    @Inject
    lateinit var getLocalWeatherUseCase: GetLocalWeatherUseCase

    private val _weatherLD = MutableLiveData<State<ArrayList<WeatherView.Data.Timelines?>>>()
    val weatherLD: LiveData<State<ArrayList<WeatherView.Data.Timelines?>>> = _weatherLD

    private val _localWeatherLD =
        MutableLiveData<State<List<WeatherView.Data.Timelines.Intervals?>>>()
    val localWeatherLD: LiveData<State<List<WeatherView.Data.Timelines.Intervals?>>> =
        _localWeatherLD

    private var getWeatherJOB: Job? = null
    private var getLocalWeatherJOB: Job? = null

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

//    fun loadLocalWeather() {
//        getLocalWeatherJOB?.cancel()
//        getLocalWeatherJOB = viewModelScope.launch(Dispatchers.IO) {
//
//            try {
//                val localWeather = getLocalWeatherUseCase.execute()
//                _localWeatherLD.postValue(localWeather)
//            } catch (exception: Exception) {
//                handleCancellableJobFailure(
//                    logMessage = "Get local weather Failure",
//                    exception = exception,
//                    onFailure = {
//                        _localWeatherLD.postValue(
//                            State.Error(data = null, message = context.getString(ErrorTypes.TECHNICAL.message))
//                        )
//                    },
//                    onJobCancellationExceptionCallback = {
//
//                    }
//                )
//            }
//        }
//    }
}