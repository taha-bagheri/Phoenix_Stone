package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load

class ProductDetailActivity : AppCompatActivity() {


    private lateinit var txtNameProduct: TextView
    private lateinit var txtDetailProduct: TextView

        private lateinit var txtPriceProduct: TextView
//    private lateinit var txtSizeProduct: TextView
//    private lateinit var txtColorProduct: TextView
    private lateinit var imageproduct: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_view)

        val logoAppProduct = findViewById<ImageView>(R.id.logoApp_product)
        findViewById<ImageButton>(R.id.shopping_cart).setOnClickListener {
            Intent(this, ShopingCartActivity::class.java).let {
                startActivity(it)
            }
        }
        findViewById<ImageButton>(R.id.profile).setOnClickListener {
            Intent(this, ProfileActivity::class.java).let {
                startActivity(it)
            }
        }

        txtNameProduct = findViewById(R.id.txt_name_product)
        txtDetailProduct = findViewById(R.id.txt_detail_product)
        txtPriceProduct = findViewById(R.id.txt_price_product)
//        txtSizeProduct = findViewById(R.id.txt_size_product)
//        txtColorProduct = findViewById(R.id.txt_color_product)
        imageproduct = findViewById(R.id.img_product)




        txtNameProduct.text = productDetail.title
        txtDetailProduct.text = productDetail.description
        txtPriceProduct.text = "${productDetail.price} €"
//                txtSizeProduct.text = productDetail.
//                txtColorProduct.text = productGlobal.color

        imageproduct.load(productDetail.images?.first())
        findViewById<Button>(R.id.btn_add).setOnClickListener {
            if (shopingList.singleOrNull { it.id == productDetail.id } == null) {
                shopingList.add(productDetail)
                Toast.makeText(this, "Agregado al carrito", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Ya esta agregado", Toast.LENGTH_SHORT).show()
            }
        }

        logoAppProduct.setOnClickListener {
            // Aquí abrimos la actividad HomePage.kt
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}