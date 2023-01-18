package com.example.instagram.Scenarios.main.post

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.*
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.main.HomeActivity
import kotlinx.android.synthetic.main.activity_chat.*

class PostRegisterActivity : AppCompatActivity() {
    private lateinit var launcher: ImagePickerLauncher

    @SuppressLint("MissingInflatedId")
    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_post_register)

        val pref = UserSharedPreferences

        val backBtn: Button = findViewById(R.id.backHome)
        val postingBtn: Button = findViewById(R.id.posting)
        val editContent: EditText = findViewById(R.id.pContent)
        var postContent = ""
        val imgSelect : ImageView = findViewById(R.id.pImage)

        backBtn.setOnClickListener {
            this.finish()
        }

        launcher = registerImagePicker { result ->
            if (result.isNotEmpty()) {
                val profileImage = result.first() // 1장만 선택하기 때문에

                // 이미지 Uri를 통해 이미지뷰에 이미지를 넣어준다.
                setProfileImage(profileImage.uri)
            }
        }
        imgSelect.setOnClickListener {
            val config = ImagePickerConfig {
                mode = ImagePickerMode.SINGLE // 1장만 선택
                isFolderMode = false
                isIncludeVideo = false
                arrowColor = Color.BLACK
                doneButtonText = "추가" // returnMode가 NONE인 경우 표시됨
                isShowCamera = true
                returnMode = ReturnMode.ALL
            }

            launcher.launch(config)
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

    private fun setProfileImage(imageUri: Uri) {
        val pImg : ImageView = findViewById(R.id.pImage)
        Glide.with(this).load(imageUri).circleCrop().into(pImg)
    }

}