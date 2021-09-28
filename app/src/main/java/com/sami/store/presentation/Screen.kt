package com.sami.store.presentation

sealed class Screen(val route: String) {
    object ProductListScreen : Screen("product_list_screen")
    object ProductDetailsScreen : Screen("product_details_screen")
    object CartDetailsScreen : Screen("cart_details_screen")
}