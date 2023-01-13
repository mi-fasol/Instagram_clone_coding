package com.example.instagram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PostAdapter() :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val pId = arrayOf("mi_fasol", "pwfkvs341d", "iowcjvpxe_awev")
    private val cId = arrayOf("eoisjfv_qohn", "poirjbg", "24rfd_ih_.1")
    private val content = arrayOf("배고프다", "히욱", "힝구링")
    private val comment = arrayOf("햐", "오타났어", "힝")

    override fun getItemCount(): Int {
        return pId.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postId.text = pId[position]
        holder.commentId.text = cId[position]
        holder.comment.text = comment[position]
        holder.postContent.text = content[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_item_recycler, parent, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var postId: TextView
        var postContent: TextView
        var commentId: TextView
        var comment: TextView
        var postImage: ImageView
        var userImage: ImageView

        init {
            postId = itemView.findViewById(R.id.postNickname)
            postContent = itemView.findViewById(R.id.postContent)
            commentId = itemView.findViewById(R.id.cNickname)
            comment = itemView.findViewById(R.id.userComment)
            postImage = itemView.findViewById(R.id.postImage)
            userImage = itemView.findViewById(R.id.profileImage)

            itemView.setOnClickListener {
            }
        }
    }
}
