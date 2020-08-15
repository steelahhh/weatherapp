package dev.steelahhh.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/*
 * Author: steelahhh
 * 15/8/20
 */

@JsonClass(generateAdapter = true)
data class WeatherOneCallResponse(
    val current: CurrentWeatherResponse?
)

@JsonClass(generateAdapter = true)
data class CurrentWeatherResponse(
    val temp: Double,
    @Json(name = "feels_like")
    val feelsLike: Double,
    val pressure: Double,
    val humidity: Double,
    val clouds: Double,
    val weather: List<WeatherDetailResponse>,
    @Json(name = "wind_speed")
    val windSpeed: Double,
    @Json(name = "wind_deg")
    val windDegree: Double,
    val uvi: Double
)

@JsonClass(generateAdapter = true)
data class WeatherDetailResponse(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)
