package dev.steelahhh.weatherapp.data.model

/*
 * Author: steelahhh
 * 15/8/20
 */

data class Weather(
    val location: String = "",
    val temperature: String,
    val temperatureFeelsLike: String,
    val icon: String,
    val description: String,
    val humidity: String
)
