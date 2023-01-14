package com.example.instagram.Scenarios.main.post

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.CommentAdapter
import com.example.instagram.Data.ImgData
import com.example.instagram.R

class CommentActivity : AppCompatActivity() {
    lateinit var commentAdapter: CommentAdapter
    var cImg = ArrayList<ImgData>()

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_comment)

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