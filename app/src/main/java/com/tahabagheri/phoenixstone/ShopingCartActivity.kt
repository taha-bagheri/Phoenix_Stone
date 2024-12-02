package com.tahabagheri.phoenixstone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShopingCartActivity : AppCompatActivity() {
    private val adapter = ShoppingAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoping_cart)
        val list = findViewById<RecyclerView>(R.id.recyclerView_products)
        list.setHasFixedSize(true)
        list.layoutManager = LinearLayoutManager(this)
        adapter.setOnProductClickListener(object : ShoppingAdapter.OnProductClickListener {
            override fun productClick(product: Product) {

            }

            override fun onRemoveItem(product: Product) {
                shopingList.remove(product)
                fillAdapter()
            }
        })
        list.adapter = adapter
        fillAdapter()
        val paypal = findViewById<Button>(R.id.paypal)
        val logoAppProducts = findViewById<ImageView>(R.id.logoApp_Products)
        logoAppProducts.setOnClickListener {
            // Aquí abrimos la actividad HomePage.kt
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        paypal.setOnClickListener {
            val url = "https://www.paypal.com/"
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }
    }

    private fun fillAdapter() {
        var totalPrice = 0
        if (shopingList.isEmpty()) {
            adapter.clear()
        } else {
            adapter.setProductList(shopingList)
            shopingList.map {
                totalPrice += it.price!!
            }
        }
        findViewById<TextView>(R.id.total_price).text = "${totalPrice} €"
    }
}