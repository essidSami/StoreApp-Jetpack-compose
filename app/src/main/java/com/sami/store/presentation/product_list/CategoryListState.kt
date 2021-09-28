package com.sami.store.presentation.product_list

data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<String> = emptyList(),
    val error: String = ""
)