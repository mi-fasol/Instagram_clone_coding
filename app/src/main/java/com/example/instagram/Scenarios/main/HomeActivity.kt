package com.example.instagram.Scenarios.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.*
import com.example.instagram.Scenarios.*
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var bottomNav = findViewById<BottomNavigationView>(R.id.bnv_main)

        bottomNav.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_home -> {
                        val homeFragment = HomeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, homeFragment).commit()
                    }
                    R.id.nav_search -> {
                        val searchFragment = SearchFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, searchFragment).commit()
                    }
                    R.id.nav_video -> {
                        val videoFragment = VideoFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, videoFragment).commit()
                    }
                    R.id.nav_shopping -> {
                        val shoppingFragment = ShoppingFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, shoppingFragment).commit()
                    }
                    else -> {
                        val myPageFragment = MyPageFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, myPageFragment).commit()
                    }
                }
                true
            }
            selectedItemId = R.id.nav_home
        }
    }
}