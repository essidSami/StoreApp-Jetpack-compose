package com.sami.store.data.repository

import com.sami.store.data.local.dao.ProductDao
import com.sami.store.data.remote.StoreApi
import com.sami.store.data.remote.dto.ProductDto
import com.sami.store.domaine.model.Product
import com.sami.store.domaine.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val api: StoreApi,
    private val dao: ProductDao
) : ProductRepository {
    override suspend fun getProducts(): List<ProductDto> =
        api.getProducts()

    override suspend fun getProductsByCategory(category: String): List<ProductDto> =
        api.getProductByCategory(category)

    override suspend fun getCategories(): List<String> =
        api.getCategories()

    override suspend fun getProductDetails(productId: Int): ProductDto =
        api.getProductDetails(productId)

    override fun getLocalProducts(): Flow<List<Product>> = dao.getProducts()

    override suspend fun insertProduct(product: Product) {
        dao.insertProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        dao.deleteProduct(product)
    }
}