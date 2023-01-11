package com.example.instagram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var bottom_nav = findViewById(R.id.bnv_main) as BottomNavigationView

        bottom_nav.run{ setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home ->{
                    val homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, homeFragment).commit()
                }
                R.id.nav_search ->{
                    val searchFragment = SearchFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, searchFragment).commit()
                }
                else -> {
                    val myPageFragment = MyPageFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, myPageFragment).commit()
                }
            }
            true
        }
        selectedItemId = R.id.nav_home
        }
    }
}