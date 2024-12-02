package com.tahabagheri.phoenixstone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ShoppingAdapter(private val items: MutableList<Product> = mutableListOf()) : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {

    private var onProductClick: OnProductClickListener? = null


    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
    }

    interface OnProductClickListener {
        fun productClick(product: Product)
        fun onRemoveItem(product: Product)
    }

    fun setOnProductClickListener(onProductClick: OnProductClickListener) {
        this.onProductClick = onProductClick
    }

    fun setProductList(productList: List<Product>) {
        this.items.clear()
        this.items.addAll(productList)
        notifyDataSetChanged()
    }
fun clear(){
    this.items.clear()
    notifyDataSetChanged()
}
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_shoping, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val products = items[i]
        viewHolder.itemName.text = products.title
        viewHolder.linearItem.setOnClickListener {
            onProductClick?.productClick(products)
        }
        viewHolder.delete.setOnClickListener {
            onProductClick?.onRemoveItem(products)
        }
        viewHolder.price.text = "${products.price.toString()} €"

        products.images?.let {
            it.first()?.let {
                Picasso.get().load(it).into(viewHolder.itemImage)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return items[position].id!!.toLong()

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.img_product)
        var delete: ImageButton = itemView.findViewById(R.id.delete)
        var itemName: TextView = itemView.findViewById(R.id.txt_product_name_item)
        var price: TextView = itemView.findViewById(R.id.txt_price)
        var linearItem = itemView.findViewById<ConstraintLayout>(R.id.linearLayoutItemProduct)
    }
}