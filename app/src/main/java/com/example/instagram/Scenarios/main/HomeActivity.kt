package com.example.instagram.Scenarios.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
                        onFragmentChange(homeFragment)
                    }
                    R.id.nav_search -> {
                        val searchFragment = SearchFragment()
                        onFragmentChange(searchFragment)
                    }
                    R.id.nav_video -> {
                        val videoFragment = VideoFragment()
                        onFragmentChange(videoFragment)
                    }
                    R.id.nav_shopping -> {
                        val shoppingFragment = ShoppingFragment()
                        onFragmentChange(shoppingFragment)
                    }
                    else -> {
                        val myPageFragment = MyPageFragment()
                        onFragmentChange(myPageFragment)
                    }
                }
                true
            }
            selectedItemId = R.id.nav_home
        }
    }

    fun onFragmentChange(frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_container, frag).commit()
    }
}