package com.example.instagram.Viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagram.Data.UserSharedPreferences
import com.example.instagram.Scenarios.intro.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignViewModel : ViewModel() {
    private var googleSignInClient: GoogleSignInClient? = null
    lateinit var auth: FirebaseAuth
    private val _loginResult = MutableSharedFlow<Boolean>()
    var loginResult = _loginResult.asSharedFlow()

    fun tryLogin(context: Context) {
        viewModelScope.launch {
            var account = async {
                getLastSignedInAccount(context)
            }
            delay(2500)
            // 계정 확인 -> true, 없음 -> false 반환
            setLoginResult(account.await() != null)
        }

    }

    // 이전에 로그인 한 계정이 있는지 확인
    private fun getLastSignedInAccount(context: Context) =
        GoogleSignIn.getLastSignedInAccount(context)

    private fun setLoginResult(isLogin: Boolean) {
        viewModelScope.launch {
            _loginResult.emit(isLogin)
        }
    }

    fun signOut(context: Context, acitivity: Activity, gso: GoogleSignInOptions) {
        googleSignInClient = GoogleSignIn.getClient(acitivity, gso)
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        googleSignInClient?.signOut()
        var intent = Intent(context, MainActivity::class.java)
        context?.startActivity(intent)
    }

    fun deleteId(context: Context, acitivity: Activity, gso: GoogleSignInOptions) {
        auth = FirebaseAuth.getInstance()
        auth.currentUser?.delete()?.addOnCompleteListener() {
            if (it.isSuccessful) {
                val pref = UserSharedPreferences
                pref.removeUser(context)
                signOut(context, acitivity, gso)
            } else {
                Toast.makeText(context, "실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}