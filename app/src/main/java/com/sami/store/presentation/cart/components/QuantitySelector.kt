package com.sami.store.presentation.cart.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sami.store.domaine.model.Product
import com.sami.store.presentation.cart.CartViewModel
import com.sami.store.presentation.ui.theme.Arapawa
import com.sami.store.presentation.ui.theme.CatskillWhite

@Composable
fun QuantitySelector(
    count: Int,
    decreaseItemCount: () -> Unit,
    increaseItemCount: () -> Unit,
    viewModel: CartViewModel,
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(2.dp, color = CatskillWhite)

    ) {
        Text(
            text = "+",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Arapawa,
            modifier = Modifier
                .padding(4.dp)
                .clickable(
                    onClick = increaseItemCount
                )
        )

        Crossfade(
            targetState = count,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
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
            viewModel.quantity.value = it
            viewModel.addProductToCart(product = product)
        }

        Text(
            text = "-",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Arapawa,
            modifier = Modifier
                .padding(4.dp)
                .clickable(
                    onClick = decreaseItemCount
                )
        )
    }
}