package com.example.instagram.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.CommentData
import com.example.instagram.Data.ImgData
import com.example.instagram.R


class CommentAdapter(private var context: Context, private val images: ArrayList<ImgData>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private val cId = arrayOf("eoisjfv_qohn", "poirjbg", "24rfd_ih_.1")
    private val comment = arrayOf("왜 그래 내새끼 ㅠㅠ 무슨 일 있어?? 어쩌ㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓ구", "힝구리링", "밥 사줄게 나와 어디야?")

    override fun getItemCount(): Int {
        return cId.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = images[position]
        holder.commentId.text = cId[position]
        holder.comment.text = comment[position]
        holder.bind(img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_item_recycler, parent, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var commentId: TextView
        var comment: TextView
        var userImage: ImageView

        private var v: View = itemView

        init {
            commentId = itemView.findViewById(R.id.cUserId)
            comment = itemView.findViewById(R.id.cInPost)
            userImage = itemView.findViewById(R.id.cUserImg)

            itemView.setOnClickListener {
            }
        }

        fun bind(imgData: ImgData){
            userImage.setImageDrawable(imgData.img)
        }
    }
}
