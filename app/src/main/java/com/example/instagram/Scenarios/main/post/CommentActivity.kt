package com.example.instagram.Scenarios.main.post

import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.CommentAdapter
import com.example.instagram.Data.ImgData
import com.example.instagram.R
import com.example.instagram.Scenarios.main.HomeActivity

class CommentActivity : AppCompatActivity() {
    lateinit var commentAdapter: CommentAdapter
    var cImg = ArrayList<ImgData>()

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_comment)

        var back : Button = findViewById(R.id.backNav)

        back.setOnClickListener {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        cImg.add(ImgData(getDrawable(R.drawable.img)!!))
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