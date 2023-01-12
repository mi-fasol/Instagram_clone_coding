package com.example.instagram

import androidx.appcompat.app.AppCompatActivity

class NotificationActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_notification)
    }
}