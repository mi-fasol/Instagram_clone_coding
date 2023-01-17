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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.GridAdapter
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.intro.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MyPageFragment : Fragment() {
    private var googleSignInClient: GoogleSignInClient? = null
    lateinit var auth: FirebaseAuth

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

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        val view: View = inflater!!.inflate(R.layout.fragment_my_page, container, false)

        view.findViewById<Button>(R.id.menu).setOnClickListener {
            val bottomSheet = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
            val bottomSheetView = BottomSheetDialog(requireContext())
            bottomSheetView.setContentView(bottomSheet)
            bottomSheetView.show()
            bottomSheet.findViewById<Button>(R.id.signOutBtn).setOnClickListener {
                signOut()
            }
            bottomSheet.findViewById<Button>(R.id.resign).setOnClickListener {
                deleteId()
            }
        }

        val pref = UserSharedPreferences

        view.findViewById<TextView>(R.id.userId).text = pref.getUserId(requireContext())
        view.findViewById<TextView>(R.id.userNick).text =
            pref.getUserNick(requireContext())

        view.findViewById<Button>(R.id.editProfile).setOnClickListener {
            signOut()
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

    private fun signOut() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        googleSignInClient?.signOut()
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
    }

    private fun deleteId() {
        auth = FirebaseAuth.getInstance()
        auth.currentUser?.delete()?.addOnCompleteListener() {
            if (it.isSuccessful) {
                val pref = UserSharedPreferences
                pref.removeUser(requireContext())
                signOut()
            } else{
                Toast.makeText(context, "실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}