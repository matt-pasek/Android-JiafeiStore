package com.example.jiafeistore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jiafeistore.ui.theme.JiafeiStoreTheme
import com.google.accompanist.pager.*
import com.google.gson.Gson
import kotlinx.coroutines.launch

class AddElementActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = Gson()
        val element = gson.fromJson(intent.getStringExtra("element"), String::class.java)
        setContent {
            JiafeiStoreTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddElement(element)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddElement(element: String) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "JiafeiStore", fontSize = 25.sp)
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ShoppingCart , "backIcon", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        }
    ) {
        if (element === "user") {
            AddUser()
        } else {
            AddProduct()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProduct() {
    Scaffold() {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUser() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column() {
            OutlinedTextField(
                value = text,
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "Email address") },
                placeholder = { Text(text = "User email") },
                onValueChange = {
                    text = it
                }
            )
        }
    }
}