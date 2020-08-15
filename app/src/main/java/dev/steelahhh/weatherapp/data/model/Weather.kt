package dev.steelahhh.weatherapp.data.model

/*
 * Author: steelahhh
 * 15/8/20
 */

data class Weather(
    val location: String = "",
    val temperature: CharSequence,
    val icon: String,
    val description: String,
    val humidity: String,
    val pressure: String,
    val windSpeed: String,
    val uvIndex: String
)
