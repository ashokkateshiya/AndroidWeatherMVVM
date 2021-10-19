package com.ashok.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import com.ashok.weatherapp.repository.network.WeatherNetworkDataSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWeatherNetworkDataSource() = WeatherNetworkDataSource()
}