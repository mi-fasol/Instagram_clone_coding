package com.example.instagram.Scenarios.chat

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.ChatAdapter
import com.example.instagram.Adapter.CommentAdapter
import com.example.instagram.Data.ChatData
import com.example.instagram.Data.ImgData
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.main.HomeActivity
import com.example.instagram.Scenarios.main.post.PostRegisterActivity

class ChatActivity : AppCompatActivity() {

    lateinit var chatAdapter: ChatAdapter
    var dImg = ArrayList<ChatData>()

    @SuppressLint("MissingInflatedId")
    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_chat)
        var back: Button = findViewById(R.id.backHome)
        var addPost: Button = findViewById(R.id.posting)
        var id: TextView = findViewById(R.id.nickname)

        var pref = UserSharedPreferences
        id.text = pref.getUserId(this)

        back.setOnClickListener {
            this.finish()
        }

        addPost.setOnClickListener {
            var intent = Intent(this, PostRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        dImg.add(ChatData(getDrawable(R.drawable.cha)!!))
        dImg.add(ChatData(getDrawable(R.drawable.img)!!))
        dImg.add(ChatData(getDrawable(R.drawable.dog)!!))

        initRecycler()
    }

    private fun initRecycler() {
        var rv: RecyclerView = findViewById(R.id.rv_chat)
        chatAdapter = ChatAdapter(this, dImg)
        rv.adapter = chatAdapter
        rv.layoutManager = LinearLayoutManager(this)
    }
}