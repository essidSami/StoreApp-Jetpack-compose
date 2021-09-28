package com.sami.store.presentation.product_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sami.store.R
import com.sami.store.presentation.product_list.ProductListViewModel
import com.sami.store.presentation.ui.theme.AlizarinCrimson
import com.sami.store.presentation.ui.theme.Arapawa
import com.sami.store.presentation.ui.theme.Eminence
import java.util.*

@Composable
fun CategoryListItem(
    category: String,
    viewModel: ProductListViewModel
) {
    var selectedCategoryIndex by remember {
        mutableStateOf("All")
    }

    var categoryImg = R.drawable.img_all_categories
    when (category) {
        "electronics" -> {
            categoryImg = R.drawable.img_electronics
        }
        "jewelery" -> {
            categoryImg = R.drawable.img_jewelery
        }
        "men's clothing" -> {
            categoryImg = R.drawable.img_men_clothing
        }
        "women's clothing" -> {
            categoryImg = R.drawable.img_women_clothing
        }
    }

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .width(128.dp)
            .padding(8.dp)
    ) {
        Box(modifier = Modifier
            .clickable {
                selectedCategoryIndex = category
                viewModel.filterProduct(category)
            }
            .background(
                color = if (category == selectedCategoryIndex)
                    Eminence
                else
                    Color.White
            )) {

            Column {
                Image(
                    painter = painterResource(
                        id = categoryImg
                    ),
                    contentDescription = category,
                    modifier = Modifier
                        .size(58.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = category.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (category == selectedCategoryIndex) Color.White
                    else Arapawa,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}