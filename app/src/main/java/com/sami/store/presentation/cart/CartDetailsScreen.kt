package com.sami.store.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sami.store.domaine.model.Product
import com.sami.store.presentation.Screen
import com.sami.store.presentation.cart.components.CartProductListItem
import com.sami.store.presentation.ui.theme.AquaHaze
import com.sami.store.presentation.ui.theme.Arapawa
import com.sami.store.presentation.ui.theme.Nepal
import com.sami.store.util.formatPrice

@Composable
fun CartDetailsScreen(
    navController: NavController,
    navigateUp: () -> Unit,
    cartViewModel: CartViewModel = hiltViewModel(),
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart") },
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
            val state = cartViewModel.stateGetProduct.value

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = AquaHaze)

            ) {
                // Create references for the composables to constrain
                val (productList, labelTotal, labelTypePayment, total) = createRefs()

                ProductsList(
                    navController = navController,
                    state = state,
                    viewModel = cartViewModel,
                    modifier = Modifier
                        .constrainAs(productList) {
                            top.linkTo(parent.top, margin = 16.dp)
                            bottom.linkTo(labelTotal.top, margin = 32.dp)
                            height = Dimension.fillToConstraints
                        })

                Text(
                    text = "Total to be collected:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Arapawa,
                    modifier = Modifier
                        .constrainAs(labelTotal) {
                            start.linkTo(parent.start, margin = 16.dp)
                            end.linkTo(total.start, margin = 12.dp)
                            bottom.linkTo(labelTypePayment.top, margin = 2.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = "Card payment",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Nepal,
                    modifier = Modifier
                        .constrainAs(labelTypePayment) {
                            start.linkTo(parent.start, margin = 16.dp)
                            end.linkTo(total.start, margin = 12.dp)
                            bottom.linkTo(parent.bottom, margin = 32.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = formatPrice( state.products?.map { it.price * it.quantity }?.sum()),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Arapawa,
                    modifier = Modifier
                        .constrainAs(total) {
                            end.linkTo(parent.end, margin = 32.dp)
                            top.linkTo(labelTotal.top)
                            bottom.linkTo(labelTypePayment.bottom)
                        }
                )
            }
        }
    )
}

@Composable
fun ProductsList(
    navController: NavController,
    state: ProductState,
    viewModel: CartViewModel,
    modifier: Modifier
) {

    LazyColumn(
    ) {
        items(state.products) { product ->
            CartProductListItem(
                product = product,
                viewModel = viewModel,
                onItemClick = {
                    navController.navigate(Screen.ProductDetailsScreen.route + "/${product.id}")
                }
            )
        }
    }
}