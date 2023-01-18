package com.example.instagram.Scenarios.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.*
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
    private var googleSignInClient: GoogleSignInClient? = null
    lateinit var auth: FirebaseAuth

    private lateinit var launcher: ImagePickerLauncher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        val view: View = inflater.inflate(R.layout.fragment_my_page, container, false)

        launcher = registerImagePicker { result ->
            if (result.isNotEmpty()) {
                val profileImage = result.first() // 1장만 선택하기 때문에

                // 이미지 Uri를 통해 이미지뷰에 이미지를 넣어준다.
                setProfileImage(profileImage.uri, view)
            }
        }

        view.findViewById<Button>(R.id.addPost).setOnClickListener {
            val config = ImagePickerConfig {
                mode = ImagePickerMode.SINGLE // 1장만 선택
                isFolderMode = false
                isIncludeVideo = false
                arrowColor = Color.WHITE
                imageTitle = "이미지를 선택하세요"
                doneButtonText = "추가" // returnMode가 NONE인 경우 표시됨
                isShowCamera = true
                returnMode = ReturnMode.ALL
            }

            launcher.launch(config)
        }

        view.findViewById<Button>(R.id.menu).setOnClickListener {
            val bottomSheet = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
            val bottomSheetView = BottomSheetDialog(requireContext())
            bottomSheetView.setContentView(bottomSheet)
            bottomSheetView.show()
            bottomSheet.findViewById<Button>(R.id.signOutBtn).setOnClickListener {
                signOut()
            }
            bottomSheet.findViewById<Button>(R.id.resign).setOnClickListener {
                deleteId(requireContext())
            }
        }

        val pref = UserSharedPreferences

        view.findViewById<TextView>(R.id.userId).text = pref.getUserId(requireContext())
        view.findViewById<TextView>(R.id.userNick).text = pref.getUserNick(requireContext())

        view.findViewById<Button>(R.id.editProfile).setOnClickListener {
            val intent = Intent(requireContext(), ProfileEditActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun setProfileImage(imageUri: Uri, view: View) {
        val pImg : ImageView = view.findViewById(R.id.pImage)
        Glide.with(this).load(imageUri).circleCrop().into(pImg)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gv = view.findViewById<RecyclerView>(R.id.gv_myPage)
        gv.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = GridAdapter()
        }
    }

    private fun signOut() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        googleSignInClient?.signOut()
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.remove(this)
            ?.commit()
        var intent = Intent(context, MainActivity::class.java)
        context?.startActivity(intent)
    }

    private fun deleteId(context: Context) {
        auth = FirebaseAuth.getInstance()
        auth.currentUser?.delete()?.addOnCompleteListener() {
            if (it.isSuccessful) {
                val pref = UserSharedPreferences
                pref.removeUser(context)
                signOut()
            } else {
                Toast.makeText(context, "실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}