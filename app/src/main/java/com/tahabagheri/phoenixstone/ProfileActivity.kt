package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var preferencesHelper: SharedPreferencesHelper
    val progressDialogHelper = ProgressDialogHelper(this)
    private lateinit var displayName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        displayName = findViewById<TextView>(R.id.name)
        preferencesHelper = SharedPreferencesHelper(this)
        auth = Firebase.auth
        auth.currentUser.let {
            displayName.text = it?.displayName
            findViewById<TextView>(R.id.email).text = it?.email
        }

        findViewById<LinearLayout>(R.id.shopping_cart).setOnClickListener {
            startActivity(Intent(this, ShopingCartActivity::class.java))
        }
        findViewById<ImageButton>(R.id.logout).setOnClickListener {
            auth.signOut()
            preferencesHelper.saveBoolean("signed", false)
            startActivity(Intent(this, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }
        displayName.setOnClickListener {
            auth.currentUser?.let { user ->
                MaterialDialog(this).show {
                    title(text = "Display name")
                    input(waitForPositiveButton = true, prefill = user.displayName) { dialog, text ->
                        val profileUpdates = userProfileChangeRequest {
                            displayName = text.toString()
                        }
                        progressDialogHelper.show("Loading, please wait...")
                        user.updateProfile(profileUpdates).addOnCompleteListener { task ->
                            displayName.text = text
                            progressDialogHelper.dismiss()
                            if (task.isSuccessful) {
                                dialog.dismiss()
                            }
                        }
                    }
                    positiveButton(text = "Confirm")
                }
            }

        }
        findViewById<LinearLayout>(R.id.favourits).setOnClickListener {
            startActivity(Intent(this, FavouriteActivity::class.java))
        }
    }
}