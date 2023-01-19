package com.example.instagram.Scenarios.main.post

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.CommentAdapter
import com.example.instagram.Data.ImgData
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.chat.ChatActivity
import com.example.instagram.Scenarios.main.HomeActivity

class CommentActivity : AppCompatActivity() {
    lateinit var commentAdapter: CommentAdapter
    private var cImg = ArrayList<ImgData>()

    @SuppressLint("MissingInflatedId")
    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_comment)

        val back : Button = findViewById(R.id.backNav)
        val dm: Button = findViewById(R.id.send)
        val postUser : TextView = findViewById(R.id.pUserId)
        val postContent : TextView = findViewById(R.id.pContent)
        val pref = UserSharedPreferences

        back.setOnClickListener {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        dm.setOnClickListener {
            var intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
            finish()
        }

        postUser.text = pref.getPostUserId(this)
        postContent.text = pref.getUserPost(this)

        cImg.add(ImgData(getDrawable(R.drawable.cat)!!))
        cImg.add(ImgData(getDrawable(R.drawable.dog)!!))
        cImg.add(ImgData(getDrawable(R.drawable.cha)!!))

        initRecycler()
    }

    private fun initRecycler(){
        var rv : RecyclerView = findViewById(R.id.comment_rv)
        commentAdapter = CommentAdapter(this, cImg)
        rv.adapter = commentAdapter
        rv.layoutManager = LinearLayoutManager(this)
    }
}