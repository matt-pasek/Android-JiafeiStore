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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
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
import coil.compose.AsyncImage
import com.example.jiafeistore.appActivity.productsList
import com.example.jiafeistore.components.PopupTopBar
import com.example.jiafeistore.ui.theme.JiafeiStoreTheme
import com.google.accompanist.pager.*
import com.google.gson.Gson

class EditProductActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = Gson()
        val product = gson.fromJson(intent.getStringExtra("product"), Product::class.java)
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
                            EditProduct(product)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProduct(product: Product) {
    val activity = (LocalContext.current as? Activity)
    var name by remember { mutableStateOf(TextFieldValue(product.name)) }
    var price by remember { mutableStateOf(TextFieldValue(product.price.toString())) }
    var description by remember { mutableStateOf(TextFieldValue(product.description)) }
    var category by remember { mutableStateOf(TextFieldValue(product.category.toString())) }
    var image by remember { mutableStateOf(TextFieldValue(product.image)) }
    Scaffold { paddingValues ->
        LazyColumn {
            item {
                AsyncImage(
                    model = product.image,
                    contentDescription = "product image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )
                OutlinedTextField(
                    value = name,
                    leadingIcon = { Icon(imageVector = Icons.Default.Create, contentDescription = null) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Product name") },
                    placeholder = { Text(text = "Name") },
                    onValueChange = {
                        name = it
                    }
                )
                OutlinedTextField(
                    value = price,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Star, contentDescription = null) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "Product price - double") },
                    placeholder = { Text(text = "Price eg. 69.69") },
                    onValueChange = {
                        price = it
                    }
                )
                OutlinedTextField(
                    value = description,
                    leadingIcon = { Icon(imageVector = Icons.Default.Info, contentDescription = null) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Product description") },
                    placeholder = { Text(text = "Description") },
                    onValueChange = {
                        description = it
                    }
                )
                OutlinedTextField(
                    value = category,
                    leadingIcon = { Icon(imageVector = Icons.Default.Info, contentDescription = null) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "Product category - int") },
                    placeholder = { Text(text = "Category eg. 0") },
                    onValueChange = {
                        category = it
                    }
                )
                OutlinedTextField(
                    value = image,
                    leadingIcon = { Icon(imageVector = Icons.Default.Info, contentDescription = null) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Product image - link") },
                    placeholder = { Text(text = "Image eg. https://... ") },
                    onValueChange = { image = it }
                )
            }
            item {
                Button(
                    onClick = {
                        val patchedProd = Product(
                            id = product.id,
                            name = name.text,
                            price = price.text.toDouble(),
                            description = description.text,
                            category = category.text.toInt(),
                            image = image.text
                        )
                        patchedProd.id?.let { ApiRequests.patchProduct(it, patchedProd) }
                        productsList.remove(product)
                        productsList.add(patchedProd)
                        activity?.finish()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Edit product",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
