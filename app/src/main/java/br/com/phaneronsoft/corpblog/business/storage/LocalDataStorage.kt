package br.com.phaneronsoft.corpblog.business.storage

import android.content.Context
import android.content.SharedPreferences

class LocalDataStorage(val context: Context) {
    companion object {
        const val PREFS_NAME = "corpBlog"
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveString(key: String, text: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(key, text)
        editor.apply()
    }

    fun saveInt(key: String, value: Int) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun saveBoolean(key: String, status: Boolean) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putBoolean(key, status)
        editor.apply()
    }

    fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    fun getInt(key: String): Int {
        return prefs.getInt(key, 0)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    fun clearAllData() {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

    fun deleteValue(key: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.remove(key)
        editor.apply()
    }
}