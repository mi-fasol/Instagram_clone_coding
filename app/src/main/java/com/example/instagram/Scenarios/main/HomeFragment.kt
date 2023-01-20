package com.example.instagram.Scenarios.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram.Adapter.PostAdapter
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.Scenarios.chat.ChatActivity
import com.example.instagram.Scenarios.main.post.PostRegisterActivity
import com.example.instagram.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.addPost.setOnClickListener {
            val intent = Intent(activity, PostRegisterActivity::class.java)
            startActivity(intent)
        }

        binding.heart.setOnClickListener {
            val intent = Intent(activity, NotificationActivity::class.java)
            startActivity(intent)
        }

        binding.dm.setOnClickListener {
            val intent = Intent(activity, ChatActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = UserSharedPreferences
        binding.rvProfile.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PostAdapter(context)
        }
        if (pref.getPostUserId(requireContext()) == null) {

        }
    }
}