package com.tahabagheri.phoenixstone

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("phoenix.json")
    fun showProduct() : Call<Products>
}