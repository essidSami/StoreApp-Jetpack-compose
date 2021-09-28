package com.sami.store.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sami.store.presentation.cart.CartDetailsScreen
import com.sami.store.presentation.product_details.ProductDetailsScreen
import com.sami.store.presentation.product_list.ProductListScreen
import com.sami.store.presentation.ui.theme.StoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ProductListScreen.route
                    ) {
                        composable(
                            route = Screen.ProductListScreen.route
                        ) {
                            ProductListScreen(navController = navController)
                        }

                        composable(
                            route = Screen.ProductDetailsScreen.route + "/{productId}"
                        ) {
                            ProductDetailsScreen(
                                navController = navController,
                                navigateUp = { navController.popBackStack() })
                        }

                        composable(
                            route = Screen.CartDetailsScreen.route
                        ) {
                            CartDetailsScreen(
                                navController = navController,
                                navigateUp = { navController.popBackStack() })
                        }
                    }

                }
            }
        }
    }
}