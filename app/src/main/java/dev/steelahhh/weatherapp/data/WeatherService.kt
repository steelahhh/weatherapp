package dev.steelahhh.weatherapp.data

import dev.steelahhh.weatherapp.data.model.WeatherOneCallResponse
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Author: steelahhh
 * 15/8/20
 */

interface WeatherService {
    @GET("onecall")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): WeatherOneCallResponse
}
