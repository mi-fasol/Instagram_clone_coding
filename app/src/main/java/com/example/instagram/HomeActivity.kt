package com.example.instagram

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var bottom_nav = findViewById(R.id.bnv_main) as BottomNavigationView

        var postBtn : Button = findViewById(R.id.addPost)
        var heartBtn : Button = findViewById(R.id.heart)
        var chatBtn : Button = findViewById(R.id.dm)

        postBtn.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
            finish()
        }

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