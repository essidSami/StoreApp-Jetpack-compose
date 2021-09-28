package com.sami.store.presentation.product_details

import com.sami.store.domaine.model.Product

data class ProductState (
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String = ""
)