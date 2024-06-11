package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class ProductPage : AppCompatActivity() {


    private lateinit var txtNameProduct : TextView
    private lateinit var txtDetailProduct : TextView
    private lateinit var txtPriceProduct : TextView
    private lateinit var txtSizeProduct : TextView
    private lateinit var txtColorProduct : TextView
    private lateinit var imageproduct : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_view)

        val logoAppProduct = findViewById<ImageView>(R.id.logoApp_product)


        txtNameProduct = findViewById(R.id.txt_name_product)
        txtDetailProduct = findViewById(R.id.txt_detail_product)
        txtPriceProduct = findViewById(R.id.txt_price_product)
        txtSizeProduct = findViewById(R.id.txt_size_product)
        txtColorProduct = findViewById(R.id.txt_color_product)
        imageproduct = findViewById(R.id.img_product)




                txtNameProduct.text = productGlobal.name
                txtDetailProduct.text = productGlobal.detail
                txtPriceProduct.text = productGlobal.price
                txtSizeProduct.text = productGlobal.size
                txtColorProduct.text = productGlobal.color

        imageproduct.load(productGlobal.imageUrl)

        logoAppProduct.setOnClickListener{
            // Aquí abrimos la actividad HomePage.kt
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

    }
}