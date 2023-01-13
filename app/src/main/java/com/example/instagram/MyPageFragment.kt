package com.example.instagram

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth

class MyPageFragment : Fragment() {
    private var googleSignInClient: GoogleSignInClient? = null
    private var auth: FirebaseAuth? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<PostAdapter.ViewHolder>? = null

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

        }

        view.findViewById<Button>(R.id.editProfile).setOnClickListener{
            signOut()
        }

        // Return the fragment view/layout
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var gv = view.findViewById<RecyclerView>(R.id.gv_myPage)
        gv.apply{
            layoutManager = GridLayoutManager(activity, 3)
            adapter = GridAdapter()
        }
    }

    private fun signOut() {
        auth?.signOut()
        googleSignInClient?.signOut()
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
    }
}