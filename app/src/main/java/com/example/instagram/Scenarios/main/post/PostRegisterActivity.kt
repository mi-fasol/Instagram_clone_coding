package com.example.instagram.Scenarios.main.post

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.main.HomeActivity
import kotlinx.android.synthetic.main.activity_chat.*

class PostRegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_post_register)

        val pref = UserSharedPreferences

        val backBtn: Button = findViewById(R.id.backHome)
        val postingBtn: Button = findViewById(R.id.posting)
        var postContent: String = ""
        val editContent: EditText = findViewById(R.id.postContent)

        backBtn.setOnClickListener {
            this.finish()
        }

        postingBtn.isEnabled = false

        editContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                postContent = editContent.text.toString()
                if (postContent != "") {
                    postingBtn.isEnabled = true
                } else {
                    Toast.makeText(
                        this@PostRegisterActivity,
                        "게시물 내용을 작성해주세요",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        postingBtn.setOnClickListener {
            pref.setUserPost(this, postContent)
            pref.setPostUserId(this, pref.getUserId(this))
            Toast.makeText(
                this@PostRegisterActivity,
                "${pref.getUserPost(this)}, Id: ${pref.getUserId(this)}",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}