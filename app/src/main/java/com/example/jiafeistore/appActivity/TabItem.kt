package com.example.jiafeistore.appActivity

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.jiafeistore.*

typealias ComposableFun = @Composable () -> Unit

var productsList: MutableList<Product> = emptyList<Product>().toMutableList()
var usersList: MutableList<User> = emptyList<User>().toMutableList()
var ordersList: MutableList<Order> = emptyList<Order>().toMutableList()
var cartList: MutableList<Product> = emptyList<Product>().toMutableList()

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
    }, "Admin Panel", { AdminPanelView(usersList, productsList) })
}
