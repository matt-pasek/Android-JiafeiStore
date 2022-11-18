package com.example.jiafeistore

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.gson.Gson


// get category name
fun getCategoryName(category: Int): String {
    return when (category) {
        1 -> "Jiafei Originals"
        2 -> "Meat"
        3 -> "Fish"
        4 -> "Juice"
        5 -> "Other"
        else -> "Unknown"
    }
}

// get user role
fun getUserRole(role: Int): String {
    return when (role) {
        1 -> "Customer"
        2 -> "Seller"
        3 -> "Admin"
        else -> "Unknown"
    }
}

@Composable
fun ShoppingView(products: List<Product>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (products.isNotEmpty()) {
            items(products.size) { index ->
                ProductCard(products[index])
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

@Preview(showBackground = true)
@Composable
fun ShoppingViewPreview() {
    ShoppingView(listOf())
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductCard(product: Product) {
    val context = LocalContext.current
    Row {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            backgroundColor = MaterialTheme.colorScheme.secondary,
            onClick = {
                val gson = Gson()
                val intent = Intent(context, ProductDetailsActivity::class.java)
                intent.putExtra("product", gson.toJson(product))
                context.startActivity(intent)
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clipToBounds(),
                    model = product.image,
                    contentDescription = null
                )
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
                Text(
                    text = product.description,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    ProductCard(Product(
        1,
        "Jiafei Originals",
        10.00,
        1,
        "https://i.imgur.com/0Z0Z9Zu.png",
        "Lorem ipsum"
    ))
}

@Composable
fun AdminPanelView(users: List<User>, products: List<Product>) {
    val checkedState = remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Products",
                fontSize = 24.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    checkedTrackColor = MaterialTheme.colorScheme.primary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.secondary
                )
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))
            Text(
                text = "Users",
                fontSize = 24.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        if (checkedState.value) {
            UsersView(users)
        } else {
            ProductsView(products)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersView(users: List<User>) {
    val context = LocalContext.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val intent = Intent(context, AddElementActivity::class.java)
                    intent.putExtra("element", "user")
                    context.startActivity(intent)
                },
            ) {
                Icon(Icons.Filled.Add, "add product")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (users.isNotEmpty()) {
                items(users.size) { index ->
                    EditUserCard(users[index])
                }
            } else {
                item {
                    Text(
                        text = "No users found",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsView(products: List<Product>) {
    val context = LocalContext.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val intent = Intent(context, AddElementActivity::class.java)
                    intent.putExtra("element", "product")
                    context.startActivity(intent)
                },
            ) {
                Icon(Icons.Filled.Add, "add product")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (products.isNotEmpty()) {
                items(products.size) { index ->
                    EditProductCard(products[index])
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditProductCard(product: Product) {
    val context = LocalContext.current
    Row {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            backgroundColor = MaterialTheme.colorScheme.secondary,
            onClick = {
                val gson = Gson()
                val intent = Intent(context, EditProductActivity::class.java)
                intent.putExtra("product", gson.toJson(product))
                context.startActivity(intent)
            }
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
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
                Column {
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
                    Text(
                        modifier = Modifier.width(300.dp),
                        text = product.description,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Button(onClick = {
                        product.id?.let { ApiRequests.deleteProduct(it.toInt()) }
                    }) {
                        Text(
                            text = "Delete",
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditUserCard(user: User) {
    val context = LocalContext.current
    Row {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            backgroundColor = MaterialTheme.colorScheme.secondary,
            onClick = {
                val gson = Gson()
                val intent = Intent(context, EditUserActivity::class.java)
                intent.putExtra("user", gson.toJson(user))
                context.startActivity(intent)
            }
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(Icons.Filled.AccountCircle , "backIcon", tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(70.dp))
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = user.name + " " + user.surName,
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        modifier = Modifier.width(300.dp),
                        text = user.email,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        modifier = Modifier.width(300.dp),
                        text = getUserRole(user.role),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
    }
}