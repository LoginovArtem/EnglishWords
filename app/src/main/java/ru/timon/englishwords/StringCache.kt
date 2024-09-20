package ru.timon.englishwords

import android.content.SharedPreferences

interface StringCache {
    fun read(): String
    fun save(text:String)

    class Base(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: String = ""
    ) : StringCache {
        override fun read(): String {
            return sharedPreferences.getString(key, default) ?: default
        }

        override fun save(text: String) {
            return sharedPreferences.edit().putString(key,text).apply()
        }

    }
}