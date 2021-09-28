package com.sami.store.presentation.product_details.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sami.store.R
import com.sami.store.presentation.cart.CartViewModel
import com.sami.store.presentation.product_details.ProductDetailsViewModel
import com.sami.store.presentation.ui.theme.Arapawa

@Composable
fun QuantitySelector(
    count: Int,
    decreaseItemCount: () -> Unit,
    increaseItemCount: () -> Unit,
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    Column {
        Row {
            Image(
                painter = painterResource(
                    id = R.drawable.img_less
                ),
                contentDescription = "less nbr product",
                modifier = Modifier
                    .size(32.dp)
                    .clickable(
                        onClick = decreaseItemCount
                    )
            )

            Crossfade(
                targetState = count,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ){
                Text(
                    text = "$it",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Arapawa,
                    modifier = Modifier
                        .widthIn(min = 24.dp)
                        .padding(
                            start = 12.dp,
                            end = 12.dp
                        )
                )
                cartViewModel.quantity.value = it
            }

            Image(
                painter = painterResource(
                    id = R.drawable.img_more
                ),
                contentDescription = "more nbr product",
                modifier = Modifier
                    .size(32.dp)
                    .clickable(
                        onClick = increaseItemCount
                    )
            )
        }


    }
}