package com.example.instagram.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.databinding.NotificationRecyclerItemBinding


class NotificationAdapter(private var context: Context) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    val pref = UserSharedPreferences

    val nId = arrayOf(
        "sv_de.luv",
        "pwfkvs341d",
        "iowcjvpxe_awev",
        "abxe_a6p",
        "bojs328",
        "_703._23"
    )

    override fun getItemCount(): Int {
        return nId.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var nickText= "${nId[position]}님이 회원님의 게시물을 좋아합니다."
        val builder = SpannableStringBuilder(nickText)

        val nickBold = StyleSpan(Typeface.BOLD)
        builder.setSpan(nickBold, 0, nId[position].length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        holder.nContent.text = builder

        holder.itemView.setOnClickListener {
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NotificationRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: NotificationRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val nContent : TextView = binding.nContent
    }
}
