package com.example.jiafeistore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Product (
    val name: String,
    val price: Int,
    val category: Int,
    //val image: Int,
    //val stars: Double,
    val description: String
)

@Composable
fun ShoppingView(products: List<Product>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .wrapContentSize(Alignment.Center)
    ) {
        products.forEach { product ->
            ProductCard(product = product)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingViewPreview() {
    ShoppingView(listOf())
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onPrimary
        //shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            //Image(painter = painterResource(product.image), contentDescription = "product image" )
            Text(
                text = product.name,
                fontSize = 24.sp,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = "$" + product.price.toString(),
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = product.category.toString(),
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = product.description,
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun AdminPanelView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Admin Panel View",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AdminPanelViewPreview() {
    AdminPanelView()
}