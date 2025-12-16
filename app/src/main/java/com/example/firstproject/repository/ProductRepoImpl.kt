package com.example.firstproject.repository

import com.example.firstproject.model.ProductModel
import com.google.firebase.database.*

class ProductRepoImpl : ProductRepo {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val ref: DatabaseReference = database.getReference("products")

    // ---------------- ADD PRODUCT ----------------
    override fun addProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        val id = ref.push().key

        if (id == null) {
            callback(false, "Failed to generate product ID")
            return
        }

        val product = model.copy(productId = id)

        ref.child(id).setValue(product)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Product added successfully")
                } else {
                    callback(false, it.exception?.message ?: "Unknown error")
                }
            }
    }

    // ---------------- UPDATE PRODUCT ----------------
    override fun updateProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        if (model.productId.isEmpty()) {
            callback(false, "Product ID is missing")
            return
        }

        ref.child(model.productId)
            .setValue(model)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Product updated successfully")
                } else {
                    callback(false, it.exception?.message ?: "Unknown error")
                }
            }
    }

    // ---------------- DELETE PRODUCT ----------------
    override fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId)
            .removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Product deleted successfully")
                } else {
                    callback(false, it.exception?.message ?: "Unknown error")
                }
            }
    }

    // ---------------- GET PRODUCT BY ID ----------------
    override fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel?) -> Unit
    ) {
        ref.child(productId)
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val product = snapshot.getValue(ProductModel::class.java)
                        callback(true, "Product fetched successfully", product)
                    } else {
                        callback(false, "Product not found", null)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(false, error.message, null)
                }
            })
    }

    // ---------------- GET ALL PRODUCTS ----------------
    override fun getAllProduct(
        callback: (Boolean, String, List<ProductModel>) -> Unit
    ) {
        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = mutableListOf<ProductModel>()

                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val product = data.getValue(ProductModel::class.java)
                        if (product != null) {
                            productList.add(product)
                        }
                    }
                }

                callback(true, "Products fetched successfully", productList)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false, error.message, emptyList())
            }
        })
    }

    // ---------------- GET PRODUCTS BY CATEGORY ----------------
    override fun getProductByCategory(
        categoryId: String,
        callback: (Boolean, String, List<ProductModel>) -> Unit
    ) {
        ref.orderByChild("categoryId")
            .equalTo(categoryId)
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val productList = mutableListOf<ProductModel>()

                    if (snapshot.exists()) {
                        for (data in snapshot.children) {
                            val product = data.getValue(ProductModel::class.java)
                            if (product != null) {
                                productList.add(product)
                            }
                        }
                    }

                    callback(true, "Products fetched successfully", productList)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(false, error.message, emptyList())
                }
            })
    }
}
