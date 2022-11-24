package com.example.jiafeistore

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jiafeistore.appActivity.usersList
import com.example.jiafeistore.components.PopupTopBar
import com.example.jiafeistore.ui.theme.JiafeiStoreTheme
import com.google.accompanist.pager.*
import com.google.gson.Gson

class EditUserActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = Gson()
        val user = gson.fromJson(intent.getStringExtra("user"), User::class.java)
        setContent {
            JiafeiStoreTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { PopupTopBar() }
                    ) { paddingValues ->
                        Column(
                            modifier = Modifier
                                .padding(paddingValues)
                                .padding(16.dp)
                        ) {
                            EditUser(user)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUser(user: User) {
    var email by remember { mutableStateOf(TextFieldValue(user.email)) }
    var password by remember { mutableStateOf(TextFieldValue(user.password)) }
    var name by remember { mutableStateOf(TextFieldValue(user.name)) }
    var surName by remember { mutableStateOf(TextFieldValue(user.surName)) }
    var roleId by remember { mutableStateOf(TextFieldValue(user.role.toString())) }
    val activity = (LocalContext.current as? Activity)
    Scaffold { paddingValues ->
        LazyColumn {
            item {
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
                    leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "User password") },
                    onValueChange = {
                        password = it
                    }
                )
                OutlinedTextField(
                    value = name,
                    leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null) },
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
                    leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null) },
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
                    leadingIcon = { Icon(imageVector = Icons.Default.Place, contentDescription = null) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "User role - as number") },
                    placeholder = { Text(text = "0 - customer, 1 - admin") },
                    onValueChange = {
                        roleId = it
                    }
                )
            }
            item {
                Button(
                    onClick = {
                        val patchedUser = User(
                            id = user.id,
                            name = name.text,
                            surName = surName.text,
                            email = email.text,
                            password = password.text,
                            role = roleId.text.toInt()
                        )
                        user.id?.let { ApiRequests.patchUser(it, patchedUser) }
                        usersList.remove(user)
                        usersList.add(patchedUser)
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
}
