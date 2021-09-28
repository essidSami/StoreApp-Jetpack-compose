package com.sami.store.presentation.product_list

import com.sami.store.domaine.model.Product

data class ProductListState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String = ""
)