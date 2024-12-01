package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var preferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        preferencesHelper = SharedPreferencesHelper(this)
        auth = Firebase.auth
        auth.currentUser.let {
            findViewById<TextView>(R.id.name).text = it?.displayName
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
        findViewById<LinearLayout>(R.id.favourits).setOnClickListener {
            startActivity(Intent(this, FavouriteActivity::class.java))
        }
    }
}