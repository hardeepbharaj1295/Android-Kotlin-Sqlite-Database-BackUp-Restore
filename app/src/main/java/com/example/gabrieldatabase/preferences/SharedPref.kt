package com.example.gabrieldatabase.preferences

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {

    lateinit var preference: SharedPreferences

    init {
        preference = context.getSharedPreferences(pref_name, pref_mode)
    }

    fun setEmail(email: String)
    {
        val shared = preference.edit()
        shared.putString("SESSION",email)
        shared.apply()
    }

    fun getEmail():String?
    {
        return preference.getString("SESSION","")
    }

    companion object{
        private val pref_name = "ANAND"
        private val pref_mode = Context.MODE_PRIVATE
    }
}