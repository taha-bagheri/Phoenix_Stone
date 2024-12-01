package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var preferencesHelper: SharedPreferencesHelper
    val progressDialogHelper = ProgressDialogHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferencesHelper = SharedPreferencesHelper(this)
        if (preferencesHelper.getBoolean("signed")) {
            startActivity(Intent(this, HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
            return
        }
        auth = Firebase.auth
        findViewById<TextView>(R.id.register).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        findViewById<MaterialButton>(R.id.login).setOnClickListener {
            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            signIn(username, password)
        }
    }

    private fun signIn(email: String, password: String) {
        progressDialogHelper.show("Loading, please wait...")
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                preferencesHelper.saveBoolean("signed", true)
                startActivity(Intent(this, HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
            } else {
                Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
            progressDialogHelper.dismiss()
        }
    }
}