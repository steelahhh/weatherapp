package dev.steelahhh.weatherapp.data

/*
 * Author: steelahhh
 * 15/8/20
 */

data class WeatherNotFoundException(
    val lat: Double,
    val lon: Double
) : Throwable("Couldn't find weather for location: ($lat,$lon)")
