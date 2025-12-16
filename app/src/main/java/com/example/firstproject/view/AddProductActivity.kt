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
import androidx.compose.runtime.livedata.observeAsState
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

    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }

    val products by productViewModel.allProducts.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        productViewModel.getAllProduct()
    }

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 15.dp)
        ) {
            item {
                Text(
                    text = "Add Product",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Product Name Field
                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    label = { Text("Product Name") },
                    placeholder = { Text("Enter product name") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = PurpleGrey80,
                        unfocusedContainerColor = PurpleGrey80,
                        focusedIndicatorColor = Blue,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))

                // Product Price Field
                OutlinedTextField(
                    value = productPrice,
                    onValueChange = { productPrice = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    label = { Text("Product Price") },
                    placeholder = { Text("Enter price") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = PurpleGrey80,
                        unfocusedContainerColor = PurpleGrey80,
                        focusedIndicatorColor = Blue,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))

                // Product Description Field
                OutlinedTextField(
                    value = productDescription,
                    onValueChange = { productDescription = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    label = { Text("Product Description") },
                    placeholder = { Text("Enter description") },
                    minLines = 3,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = PurpleGrey80,
                        unfocusedContainerColor = PurpleGrey80,
                        focusedIndicatorColor = Blue,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (productName.isBlank() || productPrice.isBlank()) {
                            Toast.makeText(
                                context,
                                "Please fill in name and price",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }

                        val price = productPrice.toDoubleOrNull()
                        if (price == null) {
                            Toast.makeText(
                                context,
                                "Invalid price format",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }

                        val product = com.example.firstproject.model.ProductModel(
                            name = productName,
                            price = price,
                            description = productDescription
                        )
                        productViewModel.addProduct(product) { success, msg ->
                            if (success) {
                                Toast.makeText(context, "Product Added", Toast.LENGTH_SHORT).show()
                                productName = ""
                                productPrice = ""
                                productDescription = ""
                                productViewModel.getAllProduct()
                            } else {
                                Toast.makeText(context, "Error: $msg", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Product")
                }

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Existing Products",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = product.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "$${product.price}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                            if (product.description.isNotBlank()) {
                                Text(
                                    text = product.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }
                        }
                        Button(
                            onClick = {
                                productViewModel.deleteProduct(product.productId) { success, msg ->
                                    if (success) {
                                        Toast.makeText(
                                            context,
                                            "Deleted ${product.name}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        productViewModel.getAllProduct()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Error: $msg",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}