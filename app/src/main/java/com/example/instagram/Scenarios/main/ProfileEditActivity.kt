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

        val editId: EditText = findViewById(R.id.registerId)
        val editNick: EditText = findViewById(R.id.registerNick)
        val backTo: Button = findViewById(R.id.toSign)

        var userId = ""
        var userNick = ""
        val loginButton: Button = findViewById(R.id.registerComplete)
        val userPref = UserSharedPreferences

        val filterId = InputFilter { source, start, end, dest, dstart, dend ->
            val ps = Pattern.compile("^[_.a-z0-9]+$")
            if (source.equals("") || ps.matcher(source).matches()) {
                return@InputFilter null
            } else {
                Toast.makeText(this, "영어 소문자 및 숫자, 특수기호 _ .만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
                return@InputFilter ""
            }
        }

        loginButton.isEnabled = false

        editId.setText(userPref.getUserId(this))
        editNick.setText(userPref.getUserNick(this))

        editId.filters = arrayOf(filterId)

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
                    loginButton.isEnabled = false
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
                if (editNick.length() in 0..20) {
                    userNick = editNick.text.toString()
                } else {
                    loginButton.isEnabled = false
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
            if(editId.text.length > 5 && editNick.text.isNotEmpty()) {
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