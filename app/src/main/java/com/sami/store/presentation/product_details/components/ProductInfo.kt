package com.sami.store.presentation.product_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.sami.store.R
import com.sami.store.domaine.model.Product
import com.sami.store.presentation.cart.CartViewModel
import com.sami.store.presentation.product_details.ProductDetailsViewModel
import com.sami.store.presentation.ui.theme.Arapawa
import com.sami.store.presentation.ui.theme.BurntSienna
import com.sami.store.presentation.ui.theme.KeyLimePie
import com.sami.store.util.formatPrice
import kotlin.math.roundToInt

@Composable
fun ProductInfo(
    product: Product,
    cartViewModel: CartViewModel
) {
    val (count, updateCount) = remember { mutableStateOf(1) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = product.title,
                modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                color = Arapawa,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = formatPrice(product.price),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    color = KeyLimePie,
                    modifier = Modifier.weight(1f)
                )
                QuantitySelector(
                    count = count,
                    decreaseItemCount = { if (count > 1) updateCount(count - 1) },
                    increaseItemCount = { updateCount(count + 1) },
                    cartViewModel = cartViewModel
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.Bottom) {

                var rateIcon = R.drawable.img_rate_five
                when (product.ratingRate.roundToInt()) {
                    1 -> {
                        rateIcon = R.drawable.img_rate_one
                    }
                    2 -> {
                        rateIcon = R.drawable.img_rate_two
                    }
                    3 -> {
                        rateIcon = R.drawable.img_rate_three
                    }
                    4 -> {
                        rateIcon = R.drawable.img_rate_four
                    }
                    5 -> {
                        rateIcon = R.drawable.img_rate_five
                    }
                }

                Image(
                    painter = painterResource(
                        id = rateIcon
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )

                Text(
                    text = "(" + product.ratingCount.toString() + ")",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    color = Arapawa,
                    style = MaterialTheme.typography.caption
                )

                val mainButtonColor = ButtonDefaults.buttonColors(
                    backgroundColor = BurntSienna,
                    contentColor = MaterialTheme.colors.surface
                )
                Button(
                    colors = mainButtonColor,
                    onClick = {
                        cartViewModel.addProductToCart(product = product)
                    },
                    elevation = ButtonDefaults.elevation()
                ) {
                    Text(text = "Add to cart")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            CategoryTag(product.category)
        }
    }

}
