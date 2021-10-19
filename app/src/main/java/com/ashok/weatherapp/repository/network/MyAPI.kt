package com.ashok.weatherapp.repository.network

import com.ashok.weatherapp.model.ForecastResponse
import com.ashok.weatherapp.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyAPI {

    @GET("data/2.5/forecast?&appid=751d80f6c314139192ffcb62c107e654")
    suspend fun getForecastByGeoCords(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String
    ): Response<ForecastResponse>

    companion object {
        operator fun invoke(): MyAPI {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.NetworkService.BASE_URL)
                .build()
                .create(MyAPI::class.java)
        }
    }
}