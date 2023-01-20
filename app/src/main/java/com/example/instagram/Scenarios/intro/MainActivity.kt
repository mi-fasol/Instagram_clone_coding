package com.example.instagram.Scenarios.intro

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.main.HomeActivity
import com.example.instagram.Viewmodel.SignViewModel
import com.example.instagram.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val viewModel by viewModels<SignViewModel>()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        var prefs = UserSharedPreferences

        viewModel.tryLogin(this)
        lifecycleScope.launchWhenCreated {
            viewModel.loginResult.collect { isLogin ->
                if (isLogin && prefs.getUserNick(this@MainActivity) != "" && prefs.getUserId(this@MainActivity) != "") {
                    toHomeActivity(auth.currentUser)
                    Log.d("shared", prefs.getUserNick(this@MainActivity))
                } else if (isLogin && (prefs.getUserNick(this@MainActivity) == "" || prefs.getUserId(
                        this@MainActivity
                    ) == "")
                ) {
                    toRegisterActivity(auth.currentUser)
                } else {
                    // 로그인 안되어있을 때 회원가입 페이지로
                    startActivity(Intent(this@MainActivity, SignInActivity::class.java))
                }
            }
        }
    }

    private fun toHomeActivity(currentUser: FirebaseUser?) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toRegisterActivity(currentUser: FirebaseUser?) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}