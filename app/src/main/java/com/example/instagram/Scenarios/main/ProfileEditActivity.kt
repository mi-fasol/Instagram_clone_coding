package com.example.instagram.Scenarios.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.intro.SignInActivity
import java.util.regex.Pattern

class ProfileEditActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

        var editId: EditText = findViewById(R.id.registerId)
        var editNick: EditText = findViewById(R.id.registerNick)
        var backTo: Button = findViewById(R.id.toSign)

        var userId = ""
        var userNick = ""
        var loginButton: Button = findViewById(R.id.registerComplete)
        var userPref = UserSharedPreferences

        var filterId = InputFilter { source, start, end, dest, dstart, dend ->
            val ps = Pattern.compile("^[_.a-z0-9]+$")
            if (!ps.matcher(source).matches()) {
                Toast.makeText(this, "영어 소문자 및 숫자, 특수기호 _ .만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
                return@InputFilter ""
            } else{
                return@InputFilter null
            }
        }

        var filterNick = InputFilter { source, start, end, dest, dstart, dend ->
            val ps = Pattern.compile("^[a-zA-Z0-9ㄱ-ㅎ가-흐ㄱ-ㅣ가-힣]+$")
            if (!ps.matcher(source).matches()) {
                Toast.makeText(this, "한글 및 영어만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
                ""
            } else{
                return@InputFilter null
            }
        }

        loginButton.isEnabled = false

        editId.filters = arrayOf(filterId)
        editNick.filters = arrayOf(filterNick)


        editId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                userId = editId.text.toString()
                loginButton.isEnabled = userId != "" && userNick != ""
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        editNick.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                userNick = editNick.text.toString()
                loginButton.isEnabled = userId != "" && userNick != ""
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        if (loginButton.isEnabled)
            loginButton.setOnClickListener {
                userPref.setUserNick(this, userNick)
                userPref.setUserId(this, userId)
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()

            }

        backTo.setOnClickListener {
            this.finish()
        }
    }
}