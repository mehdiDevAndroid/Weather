package com.devem.weather.di

import android.content.Context
import com.devem.weather.app.adapter.WeatherAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class AppModule {

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    fun provideWeatherAdapter(): WeatherAdapter {
        return WeatherAdapter(arrayListOf())
    }


}