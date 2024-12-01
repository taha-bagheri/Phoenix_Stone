package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_view)

        val imgBtnContact = findViewById<Button>(R.id.btn_contact)
        val logoAppAbout = findViewById<ImageView>(R.id.logoApp_about)


        imgBtnContact.setOnClickListener {
            // Aquí abrimos la actividad ContactPage.kt
            val intent3 = Intent(this, ContactActivity::class.java)
            startActivity(intent3)
        }

        logoAppAbout.setOnClickListener {
            // Aquí abrimos la actividad ContactPage.kt
            val intent4 = Intent(this, HomeActivity::class.java)
            intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent4)
        }
    }
}