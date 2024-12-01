package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_view)

        val imgBtnInformation = findViewById<ImageView>(R.id.img_btn_information)
        val imgBtnAbout = findViewById<ImageView>(R.id.img_btn_about)
        val imgBtnContact = findViewById<Button>(R.id.btn_contact)
        val imgBtnProducts = findViewById<ImageView>(R.id.img_btn_products)

        imgBtnInformation.setOnClickListener {
            // Aquí abrimos la actividad InformationPage.kt
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }

        imgBtnAbout.setOnClickListener {
            // Aquí abrimos la actividad AboutPage.kt
            val intent2 = Intent(this, AboutActivity::class.java)
            startActivity(intent2)
        }

        imgBtnContact.setOnClickListener {
            // Aquí abrimos la actividad ContactPage.kt
            val intent3 = Intent(this, ContactActivity::class.java)
            startActivity(intent3)
        }

        imgBtnProducts.setOnClickListener {
            // Aquí abrimos la actividad ContactPage.kt
            val intent4 = Intent(this, ProductsActivity::class.java)
            startActivity(intent4)
        }
    }
}