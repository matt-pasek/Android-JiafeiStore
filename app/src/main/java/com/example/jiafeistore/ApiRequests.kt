package com.example.jiafeistore

import android.util.Log
import com.example.jiafeistore.appActivity.ordersList
import com.example.jiafeistore.appActivity.productsList
import com.example.jiafeistore.appActivity.usersList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRequests {
    companion object {
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

        private val api: ServerApi = retrofit.create(ServerApi::class.java)

        fun getProducts() {
            api.getProducts().enqueue(object: Callback<MutableList<Product>> {
                override fun onResponse(
                    call: Call<MutableList<Product>>,
                    response: Response<MutableList<Product>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                        productsList = response.body()!!
                    }
                }

                override fun onFailure(call: Call<MutableList<Product>>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun addProduct(product: Product) {
            api.addProduct(product).enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun deleteProduct(id: Int) {
            api.deleteProduct(id).enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun updateProduct(id: Int, product: Product) {
            api.updateProduct(id, product).enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun patchProduct(id: Int, product: Product) {
            api.patchProduct(id, product).enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun getUsers() {
            api.getUsers().enqueue(object : Callback<MutableList<User>> {
                override fun onResponse(
                    call: Call<MutableList<User>>,
                    response: Response<MutableList<User>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                        usersList = response.body()!!
                    }
                }

                override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun addUser(user: User) {
            api.addUser(user).enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun deleteUser(id: Int) {
            api.deleteUser(id).enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun updateUser(id: Int, user: User) {
            api.updateUser(id, user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun patchUser(id: Int, user: User) {
            api.patchUser(id, user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun addOrder(order: Order) {
            api.addOrder(order).enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun getOrders() {
            api.getOrders().enqueue(object : Callback<MutableList<Order>> {
                override fun onResponse(
                    call: Call<MutableList<Order>>,
                    response: Response<MutableList<Order>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                        ordersList = response.body()!!
                    }
                }

                override fun onFailure(call: Call<MutableList<Order>>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun deleteOrder(id: Int) {
            api.deleteOrder(id).enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }
    }
}