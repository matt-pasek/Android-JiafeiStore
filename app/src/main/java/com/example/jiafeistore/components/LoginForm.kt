package com.example.jiafeistore.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Log in to continue",
                style = MaterialTheme.typography.titleLarge,
            )
            Row {
                OutlinedTextField(
                    value = email,
                    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { androidx.compose.material3.Text(text = "Email address") },
                    placeholder = { androidx.compose.material3.Text(text = "User email") },
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { androidx.compose.material3.Text(text = "Password") },
                    placeholder = { androidx.compose.material3.Text(text = "User password") },
                    onValueChange = {
                        password = it
                    }
                )
            }
        }

    }
}