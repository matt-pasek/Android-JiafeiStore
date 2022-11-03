package com.example.jiafeistore

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.jiafeistore.ui.theme.JiafeiStoreTheme
import com.google.accompanist.pager.*
import com.google.gson.Gson

class EditUserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = Gson()
        val user = gson.fromJson(intent.getStringExtra("user"), User::class.java)
        setContent {
            JiafeiStoreTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    EditUser(user)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUser(user: User) {
    val activity = (LocalContext.current as? Activity)
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "JiafeiStore", fontSize = 25.sp)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            activity?.finish()
                        },
                    ) {
                        Icon(Icons.Filled.Close , "close icon", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        }) {

    }
}
