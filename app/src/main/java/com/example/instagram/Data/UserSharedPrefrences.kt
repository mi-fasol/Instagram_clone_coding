package com.example.instagram.Data

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object UserSharedPreferences {
    private lateinit var pref: SharedPreferences

    fun setUserNick(context: Context, key: String, value: String){
        pref = context.getSharedPreferences("nickname", Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getUserNick(context: Context, key: String): String?{
        pref = context.getSharedPreferences("nickname", Activity.MODE_PRIVATE)
        return pref.getString(key, "")
    }

    fun setUserId(context: Context, key: String, value: String){
        pref = context.getSharedPreferences("id", Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getUserId(context: Context, key: String): String?{
        pref = context.getSharedPreferences("id", Activity.MODE_PRIVATE)
        return pref.getString(key, "")
    }

    fun removeUser(context: Context, key: String){
        pref = context.getSharedPreferences("id", Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.commit()
    }
}