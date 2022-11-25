package com.example.jiafeistore

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jiafeistore.appActivity.cartList
import com.example.jiafeistore.appActivity.getCategoryName
import com.example.jiafeistore.appActivity.productsList
import com.example.jiafeistore.components.LoginForm
import com.example.jiafeistore.components.PopupTopBar
import com.example.jiafeistore.ui.theme.JiafeiStoreTheme

class ShoppingCartActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JiafeiStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { PopupTopBar() }
                    ) { paddingValues ->
                        if (cartList.isNotEmpty()) {
                            CartView(cartList, paddingValues)
                        } else {
                            Text(text = "Your cart is empty", modifier = Modifier.padding(paddingValues))
                        }
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartView(cartItems: List<Product>, pad: PaddingValues) {
    Scaffold(
        modifier = Modifier.padding(pad),
    ) {
        paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (cartItems.isNotEmpty()) {
                items(cartItems.size) { index ->
                    CartItem(cartItems[index])
                }
            } else {
                item {
                    Text(
                        text = "No products found",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Composable
fun CartItem(product: Product) {
    val activity = LocalContext.current as Activity
    Row(
        // align the content vertically
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .clickable {
                        Log.d("Cart", "Delete item")
                        cartList.remove(product)
                        activity.recreate()
                    },
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
            ) {
                Icon(
                    Icons.Default.Delete,
                    "Delete item",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .clickable {
                        Log.d("Cart", "Delete item")
                    },
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
            ) {
                Icon(
                    Icons.Default.Delete,
                    "Delete item",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { },
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
        ) {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    AsyncImage(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        model = product.image,
                        contentDescription = "product image"
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column{
                    Text(
                        text = product.name,
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        text = "$" + product.price.toString() + " • " + getCategoryName(product.category),
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
    }
}