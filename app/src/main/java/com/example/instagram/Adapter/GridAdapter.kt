package com.example.instagram.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R

class GridAdapter() :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {
    val itemNum : Int = 8

    override fun getItemCount(): Int {
        return itemNum
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_post_gridview, parent, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var postImage: ImageView

        init {
            postImage = itemView.findViewById(R.id.myPagePost)
            itemView.setOnClickListener {
            }
        }
    }
}