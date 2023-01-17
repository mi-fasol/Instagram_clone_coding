package com.example.instagram.Scenarios.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.GridAdapter
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.intro.MainActivity
import com.example.instagram.Scenarios.intro.RegisterActivity
import com.example.instagram.Scenarios.main.post.PostRegisterActivity
import com.example.instagram.Viewmodel.SignViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth

class MyPageFragment : Fragment() {
    lateinit var viewModel: SignViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        viewModel = ViewModelProvider(this).get(SignViewModel::class.java)

        val view: View = inflater!!.inflate(R.layout.fragment_my_page, container, false)

        view.findViewById<Button>(R.id.addPost).setOnClickListener {
            startActivity(Intent(requireContext(), PostRegisterActivity::class.java))
        }

        view.findViewById<Button>(R.id.menu).setOnClickListener {
            val bottomSheet = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
            val bottomSheetView = BottomSheetDialog(requireContext())
            bottomSheetView.setContentView(bottomSheet)
            bottomSheetView.show()
            bottomSheet.findViewById<Button>(R.id.signOutBtn).setOnClickListener {
                viewModel.signOut(requireContext(), requireActivity(), gso)
            }
            bottomSheet.findViewById<Button>(R.id.resign).setOnClickListener {
                viewModel.deleteId(requireContext(), requireActivity(), gso)
            }
        }

        val pref = UserSharedPreferences

        view.findViewById<TextView>(R.id.userId).text = pref.getUserId(requireContext())
        view.findViewById<TextView>(R.id.userNick).text =
            pref.getUserNick(requireContext())

        view.findViewById<Button>(R.id.editProfile).setOnClickListener {
            startActivity(Intent(requireContext(), ProfileEditActivity::class.java))
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var gv = view.findViewById<RecyclerView>(R.id.gv_myPage)
        gv.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = GridAdapter()
        }
    }
}