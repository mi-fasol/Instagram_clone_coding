package com.example.instagram.Scenarios.main.post

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.*
import com.example.instagram.Adapter.PostAdapter
import com.example.instagram.Scenarios.*
import com.example.instagram.Scenarios.main.*

@Suppress("DEPRECATION")
class UserPostFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_user_post, container, false)

        view.findViewById<Button>(R.id.backToMyPage).setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, MyPageFragment())?.addToBackStack(null)?.commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = view.findViewById<RecyclerView>(R.id.rv_user_post)
        rv.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PostAdapter(context)
        }
    }
}