package com.example.smartbartender

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    fun saveDrinkInput(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getDrinkInput(key: String, defaultValue: String): String {
        return preferences.getString(key, defaultValue) ?: defaultValue
    }
}
