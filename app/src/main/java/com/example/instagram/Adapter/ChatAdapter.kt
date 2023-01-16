package com.example.instagram.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.ChatData
import com.example.instagram.R


class ChatAdapter(private var context: Context, private val images: ArrayList<ChatData>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    private val dId = arrayOf( "24rfd_ih_.1","eoisjfv_qohn", "poirjbg")
    private val content = arrayOf( "이따 점심 뭐 먹을래?", "새로운 메시지 3개", "새로운 메시지 7개")

    override fun getItemCount(): Int {
        return dId.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = images[position]
        holder.chatId.text = dId[position]
        holder.chatContent.text = content[position]
        holder.bind(img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_item_recycler, parent, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var chatId: TextView
        var chatContent: TextView
        var userImage: ImageView

        private var v: View = itemView

        init {
            chatId = itemView.findViewById(R.id.dUserId)
            chatContent = itemView.findViewById(R.id.dContent)
            userImage = itemView.findViewById(R.id.dUserImg)

            itemView.setOnClickListener {
            }
        }

        fun bind(chatData: ChatData){
            userImage.setImageDrawable(chatData.img)
        }
    }
}
