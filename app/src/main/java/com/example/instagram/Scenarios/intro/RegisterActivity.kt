package com.example.instagram.Scenarios.intro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.Scenarios.main.HomeActivity
import com.example.instagram.databinding.ActivityRegisterBinding
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
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

        binding.registerId.filters = arrayOf(filterId)

        binding.registerId.addTextChangedListener(object : TextWatcher {
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.registerId.length() <= 10) {
                    userId = binding.registerId.text.toString()
                    if (userId.length > 5) {
                        binding.registerComplete.isEnabled = userId != "" && userNick != ""
                    }
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "아이디는 6글자 이상 10글자 이하로 작성해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.registerId.setText(maxText)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.registerNick.addTextChangedListener(object : TextWatcher {
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.registerNick.length() <= 20) {
                    userNick = binding.registerNick.text.toString()
                    binding.registerComplete.isEnabled = userId != "" && userNick != ""
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "닉네임은 20글자를 넘을 수 없습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.registerNick.setText(maxText)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.registerComplete.setOnClickListener {
            if (userId.length > 5) {
                userPref.setUserNick(this, userNick)
                userPref.setUserId(this, userId)
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this@RegisterActivity,
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