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
import androidx.compose.ui.window.Dialog
import com.example.firstproject.model.ProductModel
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductBody() {
    val context = LocalContext.current
    val productViewModel = remember { ProductViewModel(ProductRepoImpl()) }

    // Input fields
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var categoryId by remember { mutableStateOf("") }

    var products by remember { mutableStateOf(listOf<ProductModel>()) }

    // Edit dialog state
    var showEditDialog by remember { mutableStateOf(false) }
    var editProduct by remember { mutableStateOf<ProductModel?>(null) }

    // Load products initially
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
                .padding(16.dp)
        ) {
            item {
                Text(text = "Add Product", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(20.dp))

                // ---------- Name ----------
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    placeholder = { Text("Product Name") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = PurpleGrey80,
                        unfocusedContainerColor = PurpleGrey80,
                        focusedIndicatorColor = Blue,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))

                // ---------- Price ----------
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    placeholder = { Text("Price") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = PurpleGrey80,
                        unfocusedContainerColor = PurpleGrey80,
                        focusedIndicatorColor = Blue,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))

                // ---------- Description ----------
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    placeholder = { Text("Description") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = PurpleGrey80,
                        unfocusedContainerColor = PurpleGrey80,
                        focusedIndicatorColor = Blue,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))

                // ---------- Category ID ----------
                OutlinedTextField(
                    value = categoryId,
                    onValueChange = { categoryId = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    placeholder = { Text("Category ID") },
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
                        val priceDouble = price.toDoubleOrNull() ?: 0.0
                        val product = ProductModel(
                            name = name,
                            price = priceDouble,
                            description = description,
                            categoryId = categoryId
                        )
                        productViewModel.addProduct(product) { success, msg ->
                            if (success) {
                                Toast.makeText(context, "Product Added", Toast.LENGTH_SHORT).show()
                                name = ""
                                price = ""
                                description = ""
                                categoryId = ""
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
                Text("Existing Products", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(10.dp))
            }

            items(products) { product ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(product.name)
                        Text("Price: ${product.price}")
                        Text("Category: ${product.categoryId}")
                    }

                    Row {
                        Button(onClick = {
                            // Edit product
                            editProduct = product
                            showEditDialog = true
                        }) {
                            Text("Edit")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
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

        // ---------- Edit Dialog ----------
        if (showEditDialog && editProduct != null) {
            Dialog(onDismissRequest = { showEditDialog = false }) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        var editName by remember { mutableStateOf(editProduct!!.name) }
                        var editPrice by remember { mutableStateOf(editProduct!!.price.toString()) }
                        var editDescription by remember { mutableStateOf(editProduct!!.description) }
                        var editCategory by remember { mutableStateOf(editProduct!!.categoryId) }

                        Text("Edit Product", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = editName,
                            onValueChange = { editName = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Name") }
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = editPrice,
                            onValueChange = { editPrice = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Price") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = editDescription,
                            onValueChange = { editDescription = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Description") }
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = editCategory,
                            onValueChange = { editCategory = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Category ID") }
                        )
                        Spacer(modifier = Modifier.height(15.dp))

                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TextButton(onClick = { showEditDialog = false }) {
                                Text("Cancel")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(onClick = {
                                val updatedProduct = editProduct!!.copy(
                                    name = editName,
                                    price = editPrice.toDoubleOrNull() ?: 0.0,
                                    description = editDescription,
                                    categoryId = editCategory
                                )
                                productViewModel.updateProduct(updatedProduct) { success, msg ->
                                    if (success) {
                                        Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show()
                                        productViewModel.getAllProduct()
                                        showEditDialog = false
                                    } else {
                                        Toast.makeText(context, "Error: $msg", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }) {
                                Text("Save")
                            }
                        }
                    }
                }
            }
        }
    }
}
