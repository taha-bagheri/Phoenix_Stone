package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val progressDialogHelper = ProgressDialogHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth
        findViewById<MaterialButton>(R.id.sign_up).setOnClickListener {
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            createAccount(email, password)
        }
    }

    private fun createAccount(email: String, password: String) {
        progressDialogHelper.show("Loading, please wait...")
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                updateProfile()
            } else {
                Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                progressDialogHelper.dismiss()
            }
        }
    }

    private fun updateProfile() {
        val fName = findViewById<EditText>(R.id.fist_name).text.toString()
        val lName = findViewById<EditText>(R.id.last_name).text.toString()
        val user = auth.currentUser
        val profileUpdates = userProfileChangeRequest {
            displayName = "$fName $lName"
        }

        user!!.updateProfile(profileUpdates).addOnCompleteListener { task ->
            if (task.isSuccessful) {

            }
            progressDialogHelper.dismiss()
            startActivity(Intent(this, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }
    }
}