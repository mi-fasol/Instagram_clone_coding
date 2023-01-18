package com.example.instagram.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.main.HomeActivity
import com.example.instagram.Scenarios.main.post.CommentActivity
import org.w3c.dom.Comment


class PostAdapter(private var context: Context) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    val pref = UserSharedPreferences

    private val pId = arrayOf(pref.getPostUserId(context), "pwfkvs341d", "iowcjvpxe_awev")
    private val cId = arrayOf("eoisjfv_qohn", "poirjbg", "24rfd_ih_.1")
    private val content = arrayOf(pref.getUserPost(context), "히욱", "힝구링")
    private val comment = arrayOf("오오오", "오타났어", "힝")

    override fun getItemCount(): Int {
        return pId.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postId.text = pId[position]
        holder.commentId.text = cId[position]
        holder.comment.text = comment[position]
        holder.postContent.text = content[position]
        holder.pUserId.text = pref.getPostUserId(context)
        if(holder.pUserId.text != pref.getUserId(context)){
            holder.pUserId.text = pref.getUserId(context)
            holder.postId.text = pref.getUserId(context)
        }
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
        var pUserId : TextView

        init {
            pUserId = itemView.findViewById(R.id.pUserId)
            postId = itemView.findViewById(R.id.postNickname)
            postContent = itemView.findViewById(R.id.postContent)
            commentId = itemView.findViewById(R.id.cNickname)
            comment = itemView.findViewById(R.id.userComment)
            postImage = itemView.findViewById(R.id.postImage)
            userImage = itemView.findViewById(R.id.profileImage)

            itemView.setOnClickListener {
                Intent(context, CommentActivity::class.java).apply {
                }.run {
                    context.startActivity(this)
                }
            }
        }
    }
}
