package dev.steelahhh.weatherapp.data

import android.annotation.SuppressLint
import android.content.SharedPreferences

/*
 * Author: steelahhh
 * 15/8/20
 */

@SuppressLint("ApplySharedPref")
class KeyValueStorage(
    private val sharedPreferences: SharedPreferences
) {
    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).commit()
    }
    fun getString(key: String, default: String): String {
        return sharedPreferences.getString(key, default) ?: default
    }

    fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).commit()
    }
    fun getBoolean(key: String, default: Boolean) = sharedPreferences.getBoolean(key, default)
}
