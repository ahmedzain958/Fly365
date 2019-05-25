package com.zain.fly365.base.data.local

import android.content.SharedPreferences

class AppPreference(private val preferences: SharedPreferences) {

    fun getString(key: String, defaultValue: String): String = preferences.getString(key, defaultValue)!!
    fun putString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int) = preferences.getInt(key, defaultValue)
    fun putInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

}