package com.example.instagram.Scenarios.main.post

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram.*
import com.example.instagram.Adapter.MyPostAdapter
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.Scenarios.*
import com.example.instagram.Scenarios.main.*
import com.example.instagram.databinding.FragmentUserPostBinding

@Suppress("DEPRECATION")
class UserPostFragment : Fragment() {
    private lateinit var binding: FragmentUserPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserPostBinding.inflate(inflater, container, false)
        val pref = UserSharedPreferences

        binding.backToMyPage.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, MyPageFragment())?.addToBackStack(null)?.commit()
        }

        binding.nickname.text = pref.getUserId(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvUserPost.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MyPostAdapter(context)
        }
    }
}