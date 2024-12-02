package com.tahabagheri.phoenixstone

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_view)

        val logoAppContact = findViewById<ImageView>(R.id.logoApp_contact)

        findViewById<ImageView>(R.id.img_telegram).setOnClickListener {
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/taha_rb"))
            //  telegram.setPackage("org.telegram.messenger")
            this.startActivity(Intent.createChooser(telegram, "Share with"))
        }

        findViewById<ImageView>(R.id.img_instagram).setOnClickListener {
            openInstagramPage(this, "Taha__rb")
        }
        findViewById<ImageView>(R.id.img_whatsapp).setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=" + "+34655215657"
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            this.startActivity(Intent.createChooser(i, "Share with"))
        }
        findViewById<ImageView>(R.id.img_phone).setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:+34655215657"))
            this.startActivity(Intent.createChooser(intent, "Share with"))
        }
        findViewById<ImageView>(R.id.img_map).setOnClickListener {
            val url = "https://www.google.com/maps/search/?api=1&query=" + "Parque de El Retiro, Retiro, 28009 Madrid"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        logoAppContact.setOnClickListener {
            // Aquí abrimos la actividad ContactPage.kt
            val intent4 = Intent(this, HomeActivity::class.java)
            startActivity(intent4)
        }
    }

    fun openInstagramPage(context: Context, username: String) {
        val uri = Uri.parse("http://instagram.com/_u/$username")
        val instagramIntent = Intent(Intent.ACTION_VIEW, uri)
        instagramIntent.setPackage("com.instagram.android")
        try {
            context.startActivity(instagramIntent)
        } catch (e: ActivityNotFoundException) {
            val webUri = Uri.parse("http://instagram.com/$username")
            val webIntent = Intent(Intent.ACTION_VIEW, webUri)
            context.startActivity(webIntent)
        }
    }
}