package com.example.jiafeistore

import retrofit2.Call
import retrofit2.http.*

data class Product(
    val id: Int?,
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
        const val BASE_URL = "https://b088-31-61-238-234.eu.ngrok.io/api/"
    }

    // products
    @GET("products")
    fun getProducts(): Call<MutableList<Product>>

    @GET("products/{id}")
    fun getProduct(@Path("id") id: Int): Call<Product>

    @POST("products")
    fun addProduct(@Body product: Product): Call<Product>

    @PUT("products/{id}")
    fun updateProduct(@Path("id") id: Int, @Body product: Product): Call<Product>

    @PATCH("products/{id}")
    fun patchProduct(@Path("id") id: Int, @Body product: Product): Call<Product>

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id: Int): Call<Int>

    // users
    @GET("users")
    fun getUsers(): Call<MutableList<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<User>

    @POST("users")
    fun addUser(@Body user: User): Call<Int>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: Int, @Body user: User): Call<User>

    @PATCH("users/{id}")
    fun patchUser(@Path("id") id: Int, @Body user: User): Call<User>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<User>


}