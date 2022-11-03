package com.example.jiafeistore

import retrofit2.Call
import retrofit2.http.*

data class Product (
    val name: String,
    val price: Double,
    val category: Int,
    val image: String,
    //val stars: Double,
    val description: String
)

data class User (
    val name: String,
    val surName: String,
    val password: String,
    val email: String,
    val role: Int
)

interface ServerApi {
    companion object {
        const val BASE_URL = "https://3065-2a02-a31a-c13f-3980-7164-8dfd-ec51-ba9c.eu.ngrok.io/api/"
    }

    // products
    @GET("products")
    fun getProducts(): Call<List<Product>>

    @GET("products/{id}")
    fun getProduct(@Path("id") id: Int): Call<Product>

    @POST("products")
    fun addProduct(@Body product: Product): Call<Product>

    @PUT("products/{id}")
    fun updateProduct(@Path("id") id: Int, @Body product: Product): Call<Product>

    @PATCH("products/{id}")
    fun patchProduct(@Path("id") id: Int, @Body product: Product): Call<Product>

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id: Int): Call<Product>

    // users
    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<User>

    @POST("users")
    fun addUser(@Body user: User): Call<User>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: Int, @Body user: User): Call<User>

    @PATCH("users/{id}")
    fun patchUser(@Path("id") id: Int, @Body user: User): Call<User>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<User>


}