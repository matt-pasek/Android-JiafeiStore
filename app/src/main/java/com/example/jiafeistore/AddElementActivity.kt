package com.example.jiafeistore

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
                        Icon(Icons.Filled.Close , "closeIcon", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        }
    ) {
        if (element == "user") {
            AddUser()
        } else {
            AddProduct()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProduct() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {
            OutlinedTextField(
                value = email,
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "Email address") },
                placeholder = { Text(text = "User email") },
                onValueChange = {
                    email = it
                }
            )
            OutlinedTextField(
                value = password,
                leadingIcon = { Icon(imageVector = Icons.Default.MoreVert, contentDescription = null) },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "Password") },
                placeholder = { Text(text = "User password") },
                onValueChange = {
                    password = it
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUser() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var surName by remember { mutableStateOf(TextFieldValue("")) }
    var roleId by remember { mutableStateOf(TextFieldValue("")) }
    val activity = (LocalContext.current as? Activity)
    Scaffold(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.jiafeiuser),
                contentDescription = "Add user image",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
            OutlinedTextField(
                value = email,
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "Email address") },
                placeholder = { Text(text = "User email") },
                onValueChange = {
                    email = it
                }
            )
            OutlinedTextField(
                value = password,
                leadingIcon = { Icon(imageVector = Icons.Default.MoreVert, contentDescription = null) },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "Password") },
                placeholder = { Text(text = "User password") },
                onValueChange = {
                    password = it
                }
            )
            OutlinedTextField(
                value = name,
                leadingIcon = { Icon(imageVector = Icons.Default.MoreVert, contentDescription = null) },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "Name") },
                placeholder = { Text(text = "User name") },
                onValueChange = {
                    name = it
                }
            )
            OutlinedTextField(
                value = surName,
                leadingIcon = { Icon(imageVector = Icons.Default.MoreVert, contentDescription = null) },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "Surname") },
                placeholder = { Text(text = "User surname") },
                onValueChange = {
                    surName = it
                }
            )
            OutlinedTextField(
                value = roleId,
                leadingIcon = { Icon(imageVector = Icons.Default.MoreVert, contentDescription = null) },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "User role - as number") },
                placeholder = { Text(text = "User role") },
                onValueChange = {
                    roleId = it
                }
            )
            Button(
                onClick = {
                    val user = User(
                        name = name.text,
                        surName = surName.text,
                        email = email.text,
                        password = password.text,
                        role = roleId.text.toInt()
                    )
                    ApiRequests.addUser(user)
                    usersList.add(user)
                    activity?.finish()
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Add user",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}