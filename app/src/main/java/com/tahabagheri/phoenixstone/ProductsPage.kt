package com.tahabagheri.phoenixstone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.products_view)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_products)
        val adapter = ProductAdapter()
        val logoAppProducts = findViewById<ImageView>(R.id.logoApp_Products)


        var request : ApiInterface = ApiClient().getClient().create(ApiInterface::class.java)

        request.showProduct().enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {

                val data = response.body()!!
                Log.i("LOG",data.toString())
                adapter.setProductList(data.products)
                recyclerView.adapter = adapter
                adapter.setOnProductClickListener(object : ProductAdapter.OnProductClickListener{
                    override fun productClick(productDetail2: ProductDetail2) {
                        productGlobal = productDetail2
                        val intent = Intent(this@ProductsPage, ProductPage::class.java)
                        startActivity(intent)
                    }
                })



            }

            override fun onFailure(call: Call<Products>, t: Throwable) {

                Toast.makeText(this@ProductsPage,t.message, Toast.LENGTH_LONG).show()
            }
        })



        recyclerView.layoutManager = GridLayoutManager(this, 2)

        logoAppProducts.setOnClickListener{
            // Aquí abrimos la actividad HomePage.kt
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

    }
}