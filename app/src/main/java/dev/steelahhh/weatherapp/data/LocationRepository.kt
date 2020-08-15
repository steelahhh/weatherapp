package dev.steelahhh.weatherapp.data

import javax.inject.Inject

/*
 * Author: steelahhh
 * 15/8/20
 */

class LocationRepository @Inject constructor(
    private val keyValueStorage: KeyValueStorage
) {
    companion object {
        const val DEFAULT_LAT = 52.364138
        const val DEFAULT_LNG = 4.891697
    }

    fun getLatLng(): Pair<Double, Double> {
        val latitude = keyValueStorage.getString(
            key = LATITUDE_KEY,
            default = DEFAULT_LAT.toString()
        ).toDouble()

        val longitude = keyValueStorage.getString(
            key = LONGITUDE_KEY,
            default = DEFAULT_LNG.toString()
        ).toDouble()

        return latitude to longitude
    }

    fun saveLatLng(latitude: Double, longitude: Double) {
        keyValueStorage.putString(LATITUDE_KEY, latitude.toString())
        keyValueStorage.putString(LONGITUDE_KEY, longitude.toString())
    }
}
