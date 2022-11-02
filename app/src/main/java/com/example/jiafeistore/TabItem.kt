package com.example.jiafeistore

import android.util.Log
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

typealias ComposableFun = @Composable () -> Unit

// example products list
/*
val products = listOf(
    Product(
        name = "Jiafei 1",
        price = "10.00",
        category = "Jiafei",
        image = R.drawable.jiavocado,
        stars = 4.5,
        description = "Jiafei 1 is a Jiafei product."
    ),
    Product(
        name = "Jiafei 2",
        price = "20.00",
        category = "Jiafei",
        image = R.drawable.pinkjiafei,
        stars = 4.0,
        description = "Jiafei 2 is a Jiafei product."
    ),
    Product(
        name = "Jiafei 3",
        price = "30.00",
        category = "Jiafei",
        image = R.drawable.jiavocado,
        stars = 3.5,
        description = "Jiafei 3 is a Jiafei product."
    ),
    Product(
        name = "Jiafei 4",
        price = "40.00",
        category = "Jiafei",
        image = R.drawable.pinkjiafei,
        stars = 5.0,
        description = "Jiafei 4 is a Jiafei product."
    ),
)
*/

var productsList: List<Product> = listOf() // list of products

private val interceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
private val retrofit = Retrofit.Builder()
    .baseUrl(ServerApi.BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    )
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private val api = retrofit.create(ServerApi::class.java)

private val response = api.getProducts().enqueue(object : Callback<List<Product>> {
    override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
        if (response.isSuccessful) {
            val products = response.body()
            if (products != null) {
                Log.d("JiafeiStore Sexy Database", "SUCCESS posting a fav: ${response.body()}")
                productsList = products
            }
        }
    }

    override fun onFailure(call: Call<List<Product>>, t: Throwable) {
        throw t
    }
})

sealed class TabItem(var icon: ComposableFun, var title: String, var screen: ComposableFun) {
    object Shopping : TabItem({
        Icon(
            Icons.Filled.ShoppingCart,
            "backIcon",
            tint = MaterialTheme.colorScheme.primary
        )
    }, "Shopping", { ShoppingView(productsList) })
    object Admin : TabItem({
        Icon(
            Icons.Filled.Settings,
            "backIcon",
            tint = MaterialTheme.colorScheme.primary
        )
    }, "Admin Panel", { AdminPanelView() })
}
