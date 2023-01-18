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

@Suppress("DEPRECATION")
class GridAdapter(private var context: Context) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    val itemNum: Int = 8

    override fun getItemCount(): Int {
        return itemNum
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity
                val userPostFragment = UserPostFragment()
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_container, userPostFragment).addToBackStack(null).commit()
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.user_post_gridview, parent, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var postImage: ImageView

        init {
            postImage = itemView.findViewById(R.id.myPagePost)
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