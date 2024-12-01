package com.tahabagheri.phoenixstone


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Product(
    @SerializedName("category") val category: Category?, @SerializedName("creationAt") val creationAt: String?, @SerializedName("description") val description: String?, @SerializedName("id") val id: Int?, @SerializedName("images") val images: List<String?>?, @SerializedName("price") val price: Int?, @SerializedName("title") val title: String?, var isFavourite: Boolean = false, @SerializedName("updatedAt") val updatedAt: String?
) {
    @Keep
    data class Category(
        @SerializedName("creationAt") val creationAt: String?, @SerializedName("id") val id: Int?, @SerializedName("image") val image: String?, @SerializedName("name") val name: String?, @SerializedName("updatedAt") val updatedAt: String?
    )
}