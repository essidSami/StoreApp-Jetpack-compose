package com.sami.store.domaine.use_case.get_cart

import com.sami.store.domaine.model.Product
import com.sami.store.domaine.repository.ProductRepository

class DeleteProduct (
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product) {
        repository.deleteProduct(product)
    }
}