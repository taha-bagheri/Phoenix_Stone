package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavouriteActivity : AppCompatActivity() {
    private val adapter = FavouriteAdapter()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView_products)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter.setHasStableIds(true)
        recyclerView.adapter = adapter
        adapter.setOnProductClickListener(object : FavouriteAdapter.OnProductClickListener {
            override fun productClick(productDetail2: Product) {
                productDetail = productDetail2
                val intent = Intent(this@FavouriteActivity, ProductDetailActivity::class.java)
                startActivity(intent)
            }

            override fun onRemoveFavourite(product: Product) {
                favList.remove(product)
                adapter.setProductList(favList)
            }
        })
        adapter.setProductList(favList)

        val logoAppProducts = findViewById<ImageView>(R.id.logoApp_Products)

        logoAppProducts.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}