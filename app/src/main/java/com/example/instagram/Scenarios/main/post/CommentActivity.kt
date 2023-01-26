package com.example.instagram.Scenarios.main.post

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram.Adapter.CommentAdapter
import com.example.instagram.Data.ImgData
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.chat.ChatActivity
import com.example.instagram.Scenarios.main.HomeActivity
import com.example.instagram.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommentBinding
    lateinit var commentAdapter: CommentAdapter
    private var cImg = ArrayList<ImgData>()

    @SuppressLint("MissingInflatedId")
    override fun onStart() {
        super.onStart()
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserSharedPreferences

        binding.backNav.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.send.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.pUserId.text = pref.getPostUserId(this)
        binding.pContent.text = pref.getUserPost(this)

        cImg.add(ImgData(getDrawable(R.drawable.cat)!!))
        cImg.add(ImgData(getDrawable(R.drawable.dog)!!))
        cImg.add(ImgData(getDrawable(R.drawable.cha)!!))

        initRecycler()
    }

    private fun initRecycler() {
        commentAdapter = CommentAdapter(this, cImg)
        binding.commentRv.adapter = commentAdapter
        binding.commentRv.layoutManager = LinearLayoutManager(this)
    }
}