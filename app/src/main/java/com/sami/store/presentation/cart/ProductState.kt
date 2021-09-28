package com.sami.store.presentation.cart

import com.sami.store.domaine.model.Product

data class ProductState (
    val products: List<Product> = emptyList()
)