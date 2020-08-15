package dev.steelahhh.weatherapp.data

/*
 * Author: steelahhh
 * 15/8/20
 */

data class WeatherNotFoundException(
    val latitude: Double,
    val longitude: Double
) : Throwable("Couldn't find weather for location: ($latitude,$longitude)")
