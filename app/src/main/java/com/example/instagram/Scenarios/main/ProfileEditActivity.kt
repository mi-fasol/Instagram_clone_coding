package com.example.instagram.Scenarios.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.databinding.ActivityProfileEditBinding
import java.util.regex.Pattern

class ProfileEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileEditBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userId = ""
        var userNick = ""
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

        binding.registerComplete.isEnabled = false

        binding.registerId.setText(userPref.getUserId(this))
        binding.registerNick.setText(userPref.getUserNick(this))

        binding.registerId.filters = arrayOf(filterId)

        binding.registerId.addTextChangedListener(object : TextWatcher {
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.registerId.length() <= 10) {
                    userId = binding.registerId.text.toString()
                    if (userId.length > 5 && binding.registerNick.text.isNotEmpty()) {
                        binding.registerId.isEnabled = true
                    }
                } else {
                    binding.registerId.isEnabled = false
                    Toast.makeText(
                        this@ProfileEditActivity,
                        "아이디는 6글자 이상 10글자 이하로 작성해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.registerId.setText(maxText)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.registerComplete.addTextChangedListener(object : TextWatcher {
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.registerNick.text.length in 0..20) {
                    userNick = binding.registerNick.text.toString()
                    if (userNick != "" && userId == binding.registerId.text.toString()) {
                        binding.registerId.isEnabled = true
                    }
                } else {
                    binding.registerId.isEnabled = false
                    Toast.makeText(
                        this@ProfileEditActivity,
                        "닉네임은 20글자를 넘을 수 없습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.registerNick.setText(maxText)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.registerId.setOnClickListener {
            if (binding.registerId.text.length > 5 && binding.registerNick.text.isNotEmpty()) {
                userPref.setUserNick(this, binding.registerNick.text.toString())
                userPref.setUserId(this, binding.registerId.text.toString())
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

        binding.toSign.setOnClickListener {
            this.finish()
        }
    }
}