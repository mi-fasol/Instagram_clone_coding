package com.example.instagram.Scenarios.main

import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.R
import com.example.instagram.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotificationBinding
    override fun onStart() {
        super.onStart()
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}