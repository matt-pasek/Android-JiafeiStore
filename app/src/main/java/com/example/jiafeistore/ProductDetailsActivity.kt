package com.example.jiafeistore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jiafeistore.appActivity.cartList
import com.example.jiafeistore.appActivity.getCategoryName
import com.example.jiafeistore.components.PopupTopBar
import com.example.jiafeistore.ui.theme.JiafeiStoreTheme
import com.google.accompanist.pager.*
import com.google.gson.Gson

class ProductDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = Gson()
        val product = gson.fromJson(intent.getStringExtra("product"), Product::class.java)
        Log.d("ProductDetailsActivity", "product: $product")
        setContent {
            JiafeiStoreTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductDetails(product)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetails(product: Product) {
    Scaffold(
        topBar = { PopupTopBar() },
    ){
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
        ) {
            AsyncImage(
               modifier = Modifier
                   .fillMaxWidth(),
                model = product.image,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = product.name,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "$" + product.price.toString() + " • " + getCategoryName(product.category),
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = product.description,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Button(
                    modifier = Modifier
                        .safeContentPadding()
                        .padding(top = 10.dp),
                    onClick = {
                        cartList.add(product)
                    }
                ) {
                    Icon(Icons.Filled.ShoppingCart , "buyIcon", tint = MaterialTheme.colorScheme.onPrimary)
                    Text(text = "Kup teraz")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JiafeiStoreTheme {
        ProductDetails(Product(1, "name", 20.99,1, "https://images.unsplash.com/photo-1616488000003-8b2f2f3b3f1f?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80", "description"))
    }
}