package com.tahabagheri.phoenixstone

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {



    private val images = intArrayOf(
        R.drawable.portavela,
        R.drawable.jarron,
        R.drawable.azucarero,
        R.drawable.bandeja,
        R.drawable.quema,
        R.drawable.jarrong,
        R.drawable.cenicero,
        R.drawable.cenicerog,
    )

    private lateinit var productList: List<ProductDetail2>
    private var onProductClick: OnProductClickListener? = null


    interface OnProductClickListener {
        fun productClick(productDetail2: ProductDetail2)
    }

    fun setOnProductClickListener(onProductClick: OnProductClickListener) {
        this.onProductClick = onProductClick
    }

    fun setProductList(productList: List<ProductDetail2>) {
        this.productList = productList as ArrayList<ProductDetail2>
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_product, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val products = productList[i]
        viewHolder.itemName.text = products.name
        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.linearItem.setOnClickListener{
            onProductClick?.productClick(products)
        }


    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.img_product)
        var itemName: TextView = itemView.findViewById(R.id.txt_product_name_item)
        var linearItem = itemView.findViewById<LinearLayout>(R.id.linearLayoutItemProduct)
    }
}