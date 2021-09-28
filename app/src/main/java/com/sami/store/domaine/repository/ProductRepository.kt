package com.sami.store.domaine.repository

import com.sami.store.data.remote.dto.ProductDto
import com.sami.store.domaine.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(): List<ProductDto>

    suspend fun getProductsByCategory(category: String): List<ProductDto>

    suspend fun getCategories(): List<String>

    suspend fun getProductDetails(productId: Int): ProductDto

    fun getLocalProducts(): Flow<List<Product>>

    suspend fun insertProduct(product: Product)

    suspend fun deleteProduct(product: Product)
}