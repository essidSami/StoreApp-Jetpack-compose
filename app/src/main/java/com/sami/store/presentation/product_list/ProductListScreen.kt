package com.sami.store.presentation.product_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sami.store.R
import com.sami.store.presentation.Screen
import com.sami.store.presentation.cart.CartViewModel
import com.sami.store.presentation.product_list.components.CartLayout
import com.sami.store.presentation.product_list.components.CategoryListItem
import com.sami.store.presentation.product_list.components.ProductListItem
import com.sami.store.presentation.ui.theme.AquaHaze
import com.sami.store.presentation.ui.theme.Arapawa
import com.sami.store.presentation.ui.theme.BlueHaze

@Composable
fun ProductListScreen(
    navController: NavController,
    productViewModel: ProductListViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    Surface(
        color = AquaHaze,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box {
            Column {
                Spacer(modifier = Modifier.height(20.dp))

                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    productViewModel.searchProduct(it)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Categories",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Arapawa,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                CategoriesList()

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Products",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Arapawa,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                ProductsList(navController = navController)

            }

            val cartState = cartViewModel.stateGetProduct.value
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

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(10.dp), color = Color.White)
            .shadow(0.5.dp),
        value = text,
        onValueChange = {
            text = it
            onSearch(it)
        },
        placeholder = {
            Text(
                text = "What do you want to eat today?",
                color = BlueHaze
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.img_search),
                contentDescription = "search",
                modifier = Modifier.size(24.dp)
            )
        },
        enabled = true,
        singleLine = true,
        readOnly = false,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Arapawa,
            cursorColor = Arapawa,
            leadingIconColor = BlueHaze,
            trailingIconColor = Color.White,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun CategoriesList(
    viewModel: ProductListViewModel = hiltViewModel()
) {
    val state = viewModel.stateCategory.value

    LazyRow(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)

    ) {
        val categoryList = state.categories.toMutableList().apply {
            add(0, "All")
        }

        items(categoryList) { category ->
            CategoryListItem(
                category = category,
                viewModel = viewModel
            )
        }
    }
    if (state.error.isNotBlank()) {
        Text(
            text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
//                .align(Alignment.Center)
        )
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
fun ProductsList(
    navController: NavController,
    viewModel: ProductListViewModel = hiltViewModel()
) {
    val state = viewModel.stateProduct.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        items(state.products) { product ->
            ProductListItem(navController = navController, product = product, onItemClick = {
                navController.navigate(Screen.ProductDetailsScreen.route + "/${product.id}")
            }
            )
        }
    }
    if (state.error.isNotBlank()) {
        Text(
            text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
//                .align(Alignment.Center)
        )
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