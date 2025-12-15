package com.example.firstproject.repository

import com.example.firstproject.model.ProductModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProductRepoImpl : ProductRepo {
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    val ref: DatabaseReference = database.getReference("products")
    override fun addProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        var id = ref.push().key.toString()
        model.productId = id

        ref.child(id).setValue(model).addOnCompleteListener {
            if (it.isSuccessful){
                callback(true, "Product Added Successfully")
            }else{
                callback(false, "${it.exception?.message}")
            }
        }

    }

    override fun updateProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(model.productId).updateChildren(model.toMap()).addOnCompleteListener {
            if (it.isSuccessful){
                callback(true,"Product Updated Successfully")
            }else{
                callback(false, "${it.exception?.message}")
            }
        }

    }

    override fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId).removeValue().addOnCompleteListener {
            if (it.isSuccessful){
                callback(true, "Product deleted Successfully")
            }else{
                callback(false, "${it.exception?.message}")
            }
        }

    }

    override fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel) -> Unit
    ) {
        ref.child(productId).addValueEventListener()
    }

    override fun getAllProduct(callback: (Boolean, String, List<ProductModel>) -> Unit) {

    }

    override fun getProductByCategory(
        categoryId: String,
        callback: (Boolean, String) -> Unit
    ) {

    }


}