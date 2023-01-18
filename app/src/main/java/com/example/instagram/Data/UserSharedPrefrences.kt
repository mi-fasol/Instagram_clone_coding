package com.example.instagram.Data

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object UserSharedPreferences {
    private lateinit var pref: SharedPreferences
    private var userPref : String = "userPref"
    private var postPref : String = "postPref"

    fun setUserNick(context: Context, value: String){
        pref = context.getSharedPreferences(userPref, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("nickname", value)
        editor.apply()
    }

    fun getUserNick(context: Context): String{
        pref = context.getSharedPreferences(userPref, Activity.MODE_PRIVATE)
        return pref.getString("nickname", "").toString()
    }

    fun setUserId(context: Context, value: String){
        pref = context.getSharedPreferences(userPref, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("id", value)
        editor.apply()
    }

    fun getUserPost(context: Context): String {
        pref = context.getSharedPreferences(postPref, Activity.MODE_PRIVATE)
        return pref.getString("content", "").toString()
    }

    fun setUserPost(context: Context, value: String){
        pref = context.getSharedPreferences(postPref, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("content", value)
        editor.apply()
    }

    fun getPostUserId(context: Context): String {
        pref = context.getSharedPreferences(postPref, Activity.MODE_PRIVATE)
        return pref.getString("pId", "").toString()
    }

    fun setPostUserId(context: Context, value: String){
        pref = context.getSharedPreferences(postPref, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("pId", value)
        editor.apply()
    }

    fun getUserId(context: Context): String {
        pref = context.getSharedPreferences(userPref, Activity.MODE_PRIVATE)
        return pref.getString("id", "").toString()
    }

    fun removeUser(context: Context){
        pref = context.getSharedPreferences(userPref, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }
}