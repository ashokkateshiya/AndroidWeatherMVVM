package com.ashok.weatherapp.repository.network

import com.ashok.weatherapp.model.ForecastResponse
import com.ashok.weatherapp.model.ListItem
import retrofit2.Response
import java.io.IOException

class WeatherNetworkDataSource {

    suspend fun fetchWeatherData(lat: Double, lon: Double, units:String): List<ListItem>? {

        var response: Response<ForecastResponse>? = null
        var listItems: List<ListItem>? = null

        try {
            response = MyAPI().getForecastByGeoCords(lat,lon, units)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        response?.let {
            if (response.isSuccessful) {
                listItems = response.body()?.list
            }
        }
        return listItems
    }
}