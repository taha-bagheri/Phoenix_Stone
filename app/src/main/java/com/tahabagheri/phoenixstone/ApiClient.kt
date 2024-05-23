package com.tahabagheri.phoenixstone

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    val url : String = "https://myfilminfo.ir/downloads/"

    fun getClient() : Retrofit{

        var retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
}