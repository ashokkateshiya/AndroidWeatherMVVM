package com.ashok.weatherapp.repository

import com.ashok.weatherapp.model.ListItem
import com.ashok.weatherapp.repository.network.WeatherNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor() {

    var listItems: List<ListItem>? = null

    suspend fun refreshWeatherData(lat: Double, lon:Double, units:String): List<ListItem>? {
        withContext(Dispatchers.IO) {
            async {
                listItems = WeatherNetworkDataSource().fetchWeatherData(lat, lon, units)
            }

        }
        return listItems
    }
}