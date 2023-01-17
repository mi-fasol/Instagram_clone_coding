package com.example.instagram.Scenarios.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
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
            if (source.equals("") || ps.matcher(source).matches()) {
                return@InputFilter null
            } else {
                Toast.makeText(this, "영어 소문자 및 숫자, 특수기호 _ .만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
                return@InputFilter ""
            }
        }

        var filterNick = InputFilter { source, start, end, dest, dstart, dend ->
            val ps =
                Pattern.compile("^[a-zA-Z0-9ㄱ-ㅎ가-흐ㄱ-ㅣ가-힣\\\\u318D\\\\u119E\\\\u11A2\\\\u2022\\\\u2025a\\\\u00B7\\\\uFE55]+$")
            if (source.equals("") || ps.matcher(source).matches()) {
                return@InputFilter null
            } else {
                Toast.makeText(this, "한글 및 영어만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
                return@InputFilter ""
            }
        }

        editId.setText(userPref.getUserId(this))
        editNick.setText(userPref.getUserNick(this))

        editId.filters = arrayOf(filterId)
        editNick.filters = arrayOf(filterNick)

        editId.addTextChangedListener(object : TextWatcher {
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (editId.length() <= 10) {
                    userId = editId.text.toString()
                    if (userId.length > 5) {
                        loginButton.isEnabled = true
                    }
                } else {
                    Toast.makeText(
                        this@ProfileEditActivity,
                        "아이디는 6글자 이상 10글자 이하로 작성해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                    editId.setText(maxText)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        editNick.addTextChangedListener(object : TextWatcher {
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (editNick.length() <= 20) {
                    userNick = editNick.text.toString()
                } else {
                    Toast.makeText(
                        this@ProfileEditActivity,
                        "닉네임은 20글자를 넘을 수 없습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    editNick.setText(maxText)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        loginButton.setOnClickListener {
            if(editId.text.length > 5) {
                userPref.setUserNick(this, editNick.text.toString())
                userPref.setUserId(this, editId.text.toString())
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.d("loginButton", userId.length.toString())
                Toast.makeText(
                    this@ProfileEditActivity,
                    "아이디는 6글자 이상 10글자 이하로 작성해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        backTo.setOnClickListener {
            this.finish()
        }
    }
}