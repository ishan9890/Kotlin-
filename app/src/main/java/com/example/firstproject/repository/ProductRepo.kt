package com.example.firstproject.repository

import com.example.firstproject.model.ProductModel

interface ProductRepo {

    fun addProduct(model: ProductModel, callback : (Boolean, String) -> Unit)

    fun updateProduct(model: ProductModel, callback: (Boolean, String) -> Unit)

    fun deleteProduct(productId: String, callback: (Boolean, String) -> Unit)

    fun getProductById(productId: String, callback: (Boolean, String, ProductModel) -> Unit)

    fun getAllProduct(callback: (Boolean, String, List<ProductModel>) -> Unit)

    fun getProductByCategory(
        categoryId: String,
        callback: (Boolean, String) -> Unit
    )
}