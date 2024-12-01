package com.tahabagheri.phoenixstone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
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
        list.adapter = adapter
        if (shopingList.isNotEmpty()) {
            adapter.setProductList(shopingList)
            var totalPrice = 0
            shopingList.map {
                totalPrice += it.price!!
            }
            findViewById<TextView>(R.id.total_price).setText("${totalPrice} €")
        }
        val paypal = findViewById<Button>(R.id.paypal)


        paypal.setOnClickListener {
            val url = "https://www.paypal.com/"
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }
    }
}