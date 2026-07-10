package com.pradeep.currencyconverter.core.common

interface PreferenceManager {
    fun <T> save(key: String, value: T)
    fun <T> get(key: String, defaultValue: T): T
    fun remove(key: String)
    fun clear()
}
