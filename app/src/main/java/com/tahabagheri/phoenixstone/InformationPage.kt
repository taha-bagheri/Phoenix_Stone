package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class InformationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.information_view)

        val imgBtnContact = findViewById<Button>(R.id.btn_contact)
        val logoAppInformation = findViewById<ImageView>(R.id.logoApp_information)

        imgBtnContact.setOnClickListener{
            // Aquí abrimos la actividad ContactPage.kt
            val intent3 = Intent(this, ContactPage::class.java)
            startActivity(intent3)
        }

        logoAppInformation.setOnClickListener{
            // Aquí abrimos la actividad HomePage.kt
            val intent4 = Intent(this, HomePage::class.java)
            startActivity(intent4)
        }

    }
}