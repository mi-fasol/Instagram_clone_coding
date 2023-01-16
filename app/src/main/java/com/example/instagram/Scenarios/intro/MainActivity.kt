package com.example.instagram.Scenarios.intro

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.R
import com.example.instagram.Scenarios.main.HomeActivity
import com.example.instagram.Viewmodel.SignViewModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        var prefs = UserSharedPreferences

        viewModel.tryLogin(this)
        lifecycleScope.launchWhenCreated {
            viewModel.loginResult.collect{ isLogin ->
                if (isLogin){
                    // 로그인 완료
                    if(prefs.getUserNick(this@MainActivity, "nickname")!="") {
                        toHomeActivity(auth.currentUser)
                    }
                }else{
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
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}