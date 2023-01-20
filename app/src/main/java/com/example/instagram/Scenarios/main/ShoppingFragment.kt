package com.example.instagram.Scenarios.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram.R
import com.example.instagram.databinding.FragmentShoppingBinding

class ShoppingFragment : Fragment() {
    lateinit var binding: FragmentShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingBinding.inflate(inflater, container, false)

        return binding.root
    }
}