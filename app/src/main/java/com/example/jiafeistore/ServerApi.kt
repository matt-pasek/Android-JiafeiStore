package com.example.jiafeistore

import retrofit2.Call
import retrofit2.http.*
import java.util.*

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
    val id: Int?,
    val name: String,
    val surName: String,
    val password: String,
    val email: String,
    val role: Int
)

data class Order (
    val id: Int,
    val user_id: Int,
    val date: Date,
    val products: List<OrderContent>
)

data class OrderContent (
    val product_id: Int,
    val quantity: Int,
)

interface ServerApi {
    companion object {
        const val BASE_URL = "https://6b4f-2a02-a31a-c13f-3980-d1fe-5a14-92b7-2918.eu.ngrok.io/api/"
    }

    // products
    @GET("products")
    fun getProducts(): Call<MutableList<Product>>

    @GET("products/{id}")
    fun getProduct(@Path("id") id: Int): Call<Product>

    @POST("products")
    fun addProduct(@Body product: Product): Call<Int>

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
    fun deleteUser(@Path("id") id: Int): Call<Int>

    // orders
    @GET("orders")
    fun getOrders(): Call<MutableList<Order>>

    @GET("orders/{id}")
    fun getOrderById(@Path("id") id: Int): Call<Order>

    @POST("orders")
    fun addOrder(@Body order: Order): Call<Int>

    @DELETE("orders/{id}")
    fun deleteOrder(@Path("id") id: Int): Call<Int>
}