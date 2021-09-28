package com.sami.store.data.remote

import com.sami.store.data.remote.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {

    @GET("/products")
    suspend fun getProducts(): List<ProductDto>

    @GET("/products/categories")
    suspend fun getCategories(): List<String>

    @GET("/products/categories/{category}")
    suspend fun getProductByCategory(@Path("category") category: String): List<ProductDto>

    @GET("/products/{productId}")
    suspend fun getProductDetails(@Path("productId") productId: Int): ProductDto
}