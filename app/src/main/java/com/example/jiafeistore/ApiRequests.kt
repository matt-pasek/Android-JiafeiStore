package com.example.jiafeistore

import android.util.Log
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
            api.getProducts().enqueue(object : Callback<List<Product>> {
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                        productsList = response.body()!!
                    }
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    Log.d("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun addProduct(product: Product) {
            api.addProduct(product).enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.d("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun deleteProduct(id: Int) {
            api.deleteProduct(id).enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.d("ApiRequests", "onFailure: ${t.message}")
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
                    Log.d("ApiRequests", "onFailure: ${t.message}")
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
                    Log.d("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun getUsers() {
            api.getUsers().enqueue(object : Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                        usersList = response.body()!!
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.d("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun addUser(user: User) {
            api.addUser(user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun deleteUser(id: Int) {
            api.deleteUser(id).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("ApiRequests", "onFailure: ${t.message}")
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
                    Log.d("ApiRequests", "onFailure: ${t.message}")
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
                    Log.d("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }
    }
}