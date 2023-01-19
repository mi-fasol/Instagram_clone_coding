package com.example.instagram.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.main.HomeActivity
import com.example.instagram.Scenarios.main.post.CommentActivity


class MyPostAdapter(private var context: Context) :
    RecyclerView.Adapter<MyPostAdapter.ViewHolder>() {

    val pref = UserSharedPreferences

    val pId = pref.getPostUserId(context)
    val cId = arrayOf("vasd._.4x3", "yej1n_bxx", ".1.3tg.sa")
    val content = arrayOf(pref.getUserPost(context), "아 진짜 오늘 개빡치는 하루였다고", "저메추 해조~~!!")
    val comment = arrayOf("어쩔티비", "뭔 일인데", "등촌 샤브샤브!!!!!")

    override fun getItemCount(): Int {
        return content.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.myPostId.text = pId
        holder.myCommentId.text = cId[position]
        holder.myComment.text = comment[position]
        holder.myPostContent.text = content[position]
        holder.myId.text = pId

        holder.itemView.setOnClickListener { v ->
            holder.all.setOnClickListener {
                Intent(context, CommentActivity::class.java).apply {
                }.run {
                    context.startActivity(this)
                }
            }
            holder.myPostId.setOnClickListener {
                Intent(context, CommentActivity::class.java).apply {
                }.run {
                    context.startActivity(this)
                }
            }
            holder.myPostContent.setOnClickListener {
                Intent(context, CommentActivity::class.java).apply {
                }.run {
                    context.startActivity(this)
                }
            }
            holder.myCommentId.setOnClickListener {
                Intent(context, CommentActivity::class.java).apply {
                }.run {
                    context.startActivity(this)
                }
            }
            holder.myComment.setOnClickListener {
                Intent(context, CommentActivity::class.java).apply {
                }.run {
                    context.startActivity(this)
                }
            }
            holder.mGetHeart.setOnClickListener {
                Intent(context, CommentActivity::class.java).apply {
                }.run {
                    context.startActivity(this)
                }
            }
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.my_post_recycler, parent, false)
            return ViewHolder(v)
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var myPostId: TextView
            var myPostContent: TextView
            var myCommentId: TextView
            var myComment: TextView
            var myPostImage: ImageView
            var myProfileImage: ImageView
            var myId: TextView
            private var postLayout: LinearLayout
            var all: LinearLayout
            var mGetHeart: TextView
            val menu: Button

            init {
                myId = itemView.findViewById(R.id.myId)
                myPostId = itemView.findViewById(R.id.myPostId)
                myPostContent = itemView.findViewById(R.id.myPostContent)
                myCommentId = itemView.findViewById(R.id.myCommentId)
                myComment = itemView.findViewById(R.id.myComment)
                myPostImage = itemView.findViewById(R.id.myPostImage)
                myProfileImage = itemView.findViewById(R.id.myProfileImage)
                postLayout = itemView.findViewById(R.id.mPostLayout)
                all = itemView.findViewById(R.id.mAllLayout)
                mGetHeart = itemView.findViewById(R.id.mGetHeart)
                menu = itemView.findViewById(R.id.myPostMenu)

                val showPopUp = PopupMenu(
                    context, menu
                )

                showPopUp.menu.add(Menu.NONE, 0, 0, "게시물 삭제")


                showPopUp.setOnMenuItemClickListener { menuItem ->
                    val id = menuItem.itemId
                    if (id == 0) {
                        pref.removePost(context)
                        Intent(context, HomeActivity::class.java).apply {
                        }.run {
                            context.startActivity(this)
                        }
                    }
                    false
                }

                menu.setOnClickListener {
                    showPopUp.show()
                }
            }
        }
    }
