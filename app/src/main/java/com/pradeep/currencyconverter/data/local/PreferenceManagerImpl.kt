package com.pradeep.currencyconverter.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pradeep.currencyconverter.core.common.PreferenceManager
import javax.inject.Inject

class PreferenceManagerImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : PreferenceManager {

    override fun <T> save(key: String, value: T) {
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            is Collection<*> -> editor.putString(key, gson.toJson(value))
            else -> throw IllegalArgumentException("Unsupported preference type")
        }
        editor.apply()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue) as T
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue) as T
            is Long -> sharedPreferences.getLong(key, defaultValue) as T
            is Collection<*> -> {
                val json = sharedPreferences.getString(key, null)
                if (json == null) {
                    defaultValue as T
                } else {
                    val type = object : TypeToken<List<String>>() {}.type
                    gson.fromJson(json, type) as T
                }
            }
            else -> throw IllegalArgumentException("Unsupported preference type")
        }
    }

    override fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}

