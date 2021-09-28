package com.sami.store.domaine.use_case.get_cart

import com.sami.store.domaine.model.Product
import com.sami.store.domaine.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProducts(
    private val repository: ProductRepository
) {

    operator fun invoke(): Flow<List<Product>>{
        return repository.getLocalProducts()
    }
}