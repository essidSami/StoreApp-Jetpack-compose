package com.sami.store.presentation.product_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.sami.store.R
import com.sami.store.domaine.model.Product
import com.sami.store.presentation.cart.CartViewModel
import com.sami.store.presentation.product_details.components.ProductInfo
import com.sami.store.presentation.product_list.components.CartLayout
import com.sami.store.presentation.ui.theme.AquaHaze
import com.sami.store.presentation.ui.theme.Arapawa

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    navigateUp: () -> Unit,
    productViewModel: ProductDetailsViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val productState = productViewModel.stateProduct.value
    val cartState = cartViewModel.stateGetProduct.value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details") },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = Arapawa,
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable {
                                navController.navigateUp()
                            },
                        tint = Arapawa
                    )
                }
            )
        },

        content = {
            Surface(
                color = AquaHaze,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box {
                    productState?.let {
                        DetailsView(
                            state = it,
                            cartViewModel = cartViewModel
                        )
                    }

                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        CartLayout(
                            navController = navController,
                            productList = cartState.products ?: emptyList()
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun DetailsView(
    state: ProductState,
    cartViewModel: CartViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(bottom = 16.dp)
    ) {

        state.product?.let { product ->
            item {
                product.apply {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(346.dp),
                        painter = rememberImagePainter(
                            data = image,
                            builder = {
                                crossfade(true)
                                placeholder(drawableResId = R.drawable.ic_launcher_background)
                            }
                        ),
                        alignment = Alignment.CenterStart,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    ProductInfo(
                        product = this,
                        cartViewModel = cartViewModel
                    )
                }
            }

            // description
            item {
                product.apply {

                    Spacer(modifier = Modifier.height(24.dp))
                    Title(title = "Description")
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 0.dp, 16.dp, 128.dp),
                        color = Arapawa,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }

    if (state.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 0.dp, 0.dp),
        color = Arapawa,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}






