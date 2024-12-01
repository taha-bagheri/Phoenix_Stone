package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : AppCompatActivity() {
    private val adapter = ProductAdapter()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.products_view)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView_products)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter.setHasStableIds(true)
        recyclerView.adapter = adapter
        val logoAppProducts = findViewById<ImageView>(R.id.logoApp_Products)
        findViewById<SwipeRefreshLayout>(R.id.refresh).setOnRefreshListener {
            callServer()
        }
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
        callServer()

        logoAppProducts.setOnClickListener {
            // Aquí abrimos la actividad HomePage.kt
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun callServer() {
        var request: ApiInterface = ApiClient().getClient().create(ApiInterface::class.java)
        lifecycleScope.launch {
            try {
                findViewById<SwipeRefreshLayout>(R.id.refresh).isRefreshing = true
                request.showProduct().enqueue(object : Callback<List<Product>> {
                    override fun onResponse(p0: Call<List<Product>?>, p1: Response<List<Product>?>) {
                        findViewById<SwipeRefreshLayout>(R.id.refresh).isRefreshing = false
                        val items = p1.body()
                        adapter.setProductList(items!!)
                        adapter.setOnProductClickListener(object : ProductAdapter.OnProductClickListener {
                            override fun productClick(productDetail2: Product) {
                                productDetail = productDetail2
                                val intent = Intent(this@ProductsActivity, ProductDetailActivity::class.java)
                                startActivity(intent)
                            }

                            override fun onAddFavourite(product: Product) {
                                favList.add(product)
                            }

                            override fun onRemoveFavourite(product: Product) {
                                favList.remove(product)
                            }
                        })
                    }

                    override fun onFailure(p0: Call<List<Product>?>, p1: Throwable) {
                        findViewById<SwipeRefreshLayout>(R.id.refresh).isRefreshing = false
                    }
                })
            } catch (e: Exception) {
                findViewById<SwipeRefreshLayout>(R.id.refresh).isRefreshing = false
                e.printStackTrace()
            }
        }
    }
}