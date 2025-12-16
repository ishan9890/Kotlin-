package com.example.firstproject.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.firstproject.repository.ProductRepoImpl
import com.example.firstproject.ui.theme.PurpleGrey80
import com.example.firstproject.viewmodel.ProductViewModel

class AddProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AddProductBody()
        }
    }
}

@Composable
fun AddProductBody() {
    val context = LocalContext.current
    val productViewModel = remember { ProductViewModel(ProductRepoImpl()) }

    var email by remember { mutableStateOf("") }
    var products by remember { mutableStateOf(listOf<com.example.firstproject.model.ProductModel>()) }

    LaunchedEffect(Unit) {
        productViewModel.getAllProduct()
    }

    productViewModel.allProducts.observeForever {
        it?.let { list -> products = list }
    }

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                Text(text = "Add Product")
                Spacer(modifier = Modifier.height(20.dp))

                repeat(3) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        shape = RoundedCornerShape(15.dp),
                        placeholder = { Text("abc@gmail.com") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = PurpleGrey80,
                            unfocusedContainerColor = PurpleGrey80,
                            focusedIndicatorColor = Blue,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                Button(
                    onClick = {
                        val product = com.example.firstproject.model.ProductModel(
                            name = email,
                            price = 0.0,
                            description = ""
                        )
                        productViewModel.addProduct(product) { success, msg ->
                            if (success) {
                                Toast.makeText(context, "Product Added", Toast.LENGTH_SHORT).show()
                                email = ""
                                productViewModel.getAllProduct()
                            } else {
                                Toast.makeText(context, "Error: $msg", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text("Add Product")
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text("Existing Products")
                Spacer(modifier = Modifier.height(10.dp))
            }


            items(products) { product ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(product.name)
                    Button(onClick = {
                        productViewModel.deleteProduct(product.productId) { success, msg ->
                            if (success) {
                                Toast.makeText(context, "Deleted ${product.name}", Toast.LENGTH_SHORT).show()
                                productViewModel.getAllProduct()
                            } else {
                                Toast.makeText(context, "Error: $msg", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}