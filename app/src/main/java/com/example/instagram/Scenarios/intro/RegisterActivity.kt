package com.example.instagram.Scenarios.intro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.R
import com.example.instagram.Scenarios.main.HomeActivity

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var editId: EditText = findViewById(R.id.registerId)
        var editNick: EditText = findViewById(R.id.registerNick)

        var userId = ""
        var userNick = ""
        var loginButton: Button = findViewById(R.id.registerComplete)

        loginButton.isEnabled = false

        editId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                userId = editId.text.toString()
                if (userId != "" && userNick != "") {
                    loginButton.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        editNick.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                userNick = editNick.text.toString()
                if (userId != "" && userNick != "") {
                    loginButton.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        loginButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}