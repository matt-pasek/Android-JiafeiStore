package com.example.jiafeistore

import com.google.gson.annotations.JsonAdapter
import retrofit2.Call
import retrofit2.http.*
import java.sql.Types

interface ServerApi {
    companion object {
        const val BASE_URL = "https://90f8-2a02-a31a-c13f-3980-f977-6fe4-8688-d1e6.eu.ngrok.io/api/"
    }

    // get all products
    @GET("products")
    fun getProducts(): Call<List<Product>>
}