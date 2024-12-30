package com.devem.weather.di

import com.devem.weather.app.adapter.WeatherAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class AdapterModule {

    @Provides
    fun provideWeatherAdapter(): WeatherAdapter {
        return WeatherAdapter(arrayListOf())
    }
}