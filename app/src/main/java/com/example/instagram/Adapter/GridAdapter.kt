package com.example.instagram.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.Scenarios.main.post.UserPostFragment
import com.example.instagram.databinding.NotificationRecyclerItemBinding
import com.example.instagram.databinding.UserPostGridviewBinding

@Suppress("DEPRECATION")
class GridAdapter(private var context: Context) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    val itemNum: Int = 8

    override fun getItemCount(): Int {
        return itemNum
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val userPostFragment = UserPostFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, userPostFragment).commit()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserPostGridviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: UserPostGridviewBinding) : RecyclerView.ViewHolder(binding.root) {
        private var postImage: ImageView

        init {
            postImage = binding.myPagePost
        }
    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var listener: ItemClickListener
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.listener = itemClickListener
    }
}