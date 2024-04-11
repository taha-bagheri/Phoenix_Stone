package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ContactPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_view)

        val logoAppContact = findViewById<ImageView>(R.id.logoApp_contact)


        logoAppContact.setOnClickListener{
            // Aquí abrimos la actividad ContactPage.kt
            val intent4 = Intent(this, HomePage::class.java)
            startActivity(intent4)
        }
    }
}