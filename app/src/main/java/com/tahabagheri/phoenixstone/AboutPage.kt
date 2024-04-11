package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AboutPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_view)

        val imgBtnContact = findViewById<Button>(R.id.btn_contact)
        val logoAppAbout = findViewById<ImageView>(R.id.logoApp_about)


        imgBtnContact.setOnClickListener{
            // Aquí abrimos la actividad ContactPage.kt
            val intent3 = Intent(this, ContactPage::class.java)
            startActivity(intent3)
        }

        logoAppAbout.setOnClickListener{
            // Aquí abrimos la actividad ContactPage.kt
            val intent4 = Intent(this, HomePage::class.java)
            startActivity(intent4)
        }
    }
}