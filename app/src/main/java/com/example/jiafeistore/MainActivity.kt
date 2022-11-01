package com.example.jiafeistore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jiafeistore.ui.theme.JiafeiStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JiafeiStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldWithTopBar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBar() {
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
        }, content = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.shopping), contentDescription = "Online shopping image" )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Nie kupuj kota w worku!",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "Znajdź coś dla siebie. Przetestuj z możliwością darmowego zwrotu.",
                    color = MaterialTheme.colorScheme.onBackground
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "•  Najlepsze produkty",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "•  Przyjemnie",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "•  Modnie",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Zacznij zakupy",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        })
}
