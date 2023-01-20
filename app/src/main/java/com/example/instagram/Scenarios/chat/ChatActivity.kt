package com.example.instagram.Scenarios.chat

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram.Adapter.ChatAdapter
import com.example.instagram.Data.ChatData
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.main.post.PostRegisterActivity
import com.example.instagram.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatBinding
    lateinit var chatAdapter: ChatAdapter
    var dImg = ArrayList<ChatData>()

    @SuppressLint("MissingInflatedId")
    override fun onStart() {
        super.onStart()
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = UserSharedPreferences
        binding.nickname.text = pref.getUserId(this)

        binding.backHome.setOnClickListener {
            this.finish()
        }

        binding.posting.setOnClickListener {
            var intent = Intent(this, PostRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        dImg.add(ChatData(getDrawable(R.drawable.cha)!!))
        dImg.add(ChatData(getDrawable(R.drawable.cat)!!))
        dImg.add(ChatData(getDrawable(R.drawable.dog)!!))

        initRecycler()
    }

    private fun initRecycler() {
        chatAdapter = ChatAdapter(this, dImg)
        binding.rvChat.adapter = chatAdapter
        binding.rvChat.layoutManager = LinearLayoutManager(this)
    }
}