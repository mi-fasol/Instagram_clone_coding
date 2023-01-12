package com.example.instagram

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.PostData
import com.example.instagram.Data.UserData


//class PostAdapter(var user: Array<UserData>, var post: Array<PostData>, val con: Context) :
//    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
//    var TAG = "PostAdapter"
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        lateinit var iv_person_phone_book_list_item: ImageView
//        lateinit var tv_name_phone_book_list_item: TextView
//        lateinit var tv_phone_number_phone_book_list_item: TextView
//
//    init {
//        iv_person_phone_book_list_item = itemView.findViewById(R.id.iv_person_phone_book_list_item)
//        tv_name_phone_book_list_item = itemView.findViewById(R.id.tv_name_phone_book_list_item)
//        tv_phone_number_phone_book_list_item =
//            itemView.findViewById(R.id.tv_phone_number_phone_book_list_item)
//
//        itemView.setOnClickListener {
//            AlertDialog.Builder(con).apply {
//                var position = adapterPosition
//                var user = user[position]
//                var post = post[position]
//                setTitle(user.id)
//                setMessage(post.content)
//                setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
//                    Toast.makeText(con, "OK Button Click", Toast.LENGTH_SHORT).show()
//                })
//                show()
//            }
//        }
//    }
//}
//
//override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//    val con = parent.context
//    val inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//    val view = inflater.inflate(R.layout.post_item_recycler, parent, false)
//
//    return ViewHolder(view)
//}
//
//override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//    val user: UserData = user[position]
//    val post: PostData = post[position]
//    //[수정요함] 이미지 작업의 경우 glide를 사용해 server의 image를 불러올 것
//    //holder.iv_person_phone_book_list_item
//    holder.tv_name_phone_book_list_item.text = user.nickname
//    holder.tv_phone_number_phone_book_list_item.text = post.content
//}
//
//override fun getItemCount(): Int {
//    return user.size
//}
//}
