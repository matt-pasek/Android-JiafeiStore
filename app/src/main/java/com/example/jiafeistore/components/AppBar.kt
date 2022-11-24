package com.example.jiafeistore.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.jiafeistore.ApiRequests
import com.example.jiafeistore.ShoppingCartActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupTopBar() {
    val activity = (LocalContext.current as? Activity)
    TopAppBar(
        title = {
            Text(text = "JiafeiStore", fontSize = 25.sp)
        },
        navigationIcon = {
            IconButton(onClick = {
                activity?.finish()
            }) {
                Icon(Icons.Filled.Close , "backIcon", tint = MaterialTheme.colorScheme.onPrimary)
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(text = "JiafeiStore", fontSize = 25.sp)
        },
        navigationIcon = {
            IconButton(
                onClick = {},
                enabled = false,
            ) {
                Icon(Icons.Filled.ShoppingCart , "backIcon", tint = MaterialTheme.colorScheme.onPrimary)
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    val activity = (LocalContext.current as? Activity)
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(text = "JiafeiStore", fontSize = 25.sp)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    activity?.finish()
                }
            ) {
                Icon(Icons.Filled.ArrowBack , "backIcon", tint = MaterialTheme.colorScheme.onPrimary)
            }
        },
        actions = {
            IconButton(
                onClick = {
                    ApiRequests.getProducts()
                    ApiRequests.getUsers()
                    activity?.recreate()
                }
            ) {
                Icon(Icons.Filled.Refresh , "refreshIcon", tint = MaterialTheme.colorScheme.onPrimary)
            }
            IconButton(
                onClick = {
                    activity?.startActivity(Intent(activity, ShoppingCartActivity::class.java))
                }
            ) {
                Icon(Icons.Filled.ShoppingCart , "cartIcon", tint = MaterialTheme.colorScheme.onPrimary)
            }
        },
        colors = topAppBarColors (
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
    )
}
