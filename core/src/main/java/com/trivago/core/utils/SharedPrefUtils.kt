package com.trivago.core.utils

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

abstract class SharedPreferenceLiveData<T>(
    val sharedPreferences: SharedPreferences,
    private val key: String,
    private val defValue: String
) : LiveData<T>() {

    private val onSharedPreferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == this.key) {
                value = getValueFromSharedPreferences(key, defValue)
            }
        }

    abstract fun getValueFromSharedPreferences(key: String, defValue: String): T

    override fun onActive() {
        super.onActive()
        value = getValueFromSharedPreferences(key, defValue)
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
    }

    override fun onInactive() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(
            onSharedPreferenceChangeListener
        )
        super.onInactive()
    }
}

class TrivagoSharedPreferenceLiveData(
    sharedPreferences: SharedPreferences,
    key: String,
    defValue: String
) : SharedPreferenceLiveData<String?>(sharedPreferences, key, defValue) {

    override fun getValueFromSharedPreferences(key: String, defValue: String): String? =
        sharedPreferences.getString(key, defValue)
}