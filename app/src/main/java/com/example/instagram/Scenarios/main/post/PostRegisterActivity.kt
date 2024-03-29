package com.example.instagram.Scenarios.main.post

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.esafirm.imagepicker.features.*
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.Scenarios.main.HomeActivity
import com.example.instagram.databinding.ActivityPostRegisterBinding
import kotlinx.android.synthetic.main.activity_chat.*

@Suppress("DEPRECATION")
class PostRegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityPostRegisterBinding
    private val GALLERY = 1

    @SuppressLint("MissingInflatedId")
    override fun onStart() {
        super.onStart()
        binding = ActivityPostRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserSharedPreferences

        var postContent = ""

        binding.backHome.setOnClickListener {
            this.finish()
        }

        binding.pImage.setOnClickListener {
            navigatePhotos()
        }

        binding.posting.isEnabled = false

        binding.pContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                postContent = binding.pContent.text.toString()
                if (postContent != "") {
                    binding.posting.isEnabled = true
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

        binding.posting.setOnClickListener {
            pref.setUserPost(this, postContent)
            pref.setPostUserId(this, pref.getUserId(this))
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    fun navigatePhotos() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY)
    }

    @Deprecated("Deprecated in Java")
    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY) {
                val imageData: Uri? = data?.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageData)
                    binding.pImage.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}