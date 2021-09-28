package com.sami.store.presentation.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.sami.store.domaine.model.Product
import com.sami.store.presentation.cart.CartViewModel
import com.sami.store.presentation.ui.theme.Arapawa
import com.sami.store.util.formatPrice

@Composable
fun CartProductListItem(
    product: Product,
    viewModel: CartViewModel,
    onItemClick: (Product) -> Unit
) {
    val (count, updateCount) = remember { mutableStateOf(product.quantity) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(product) }
            .background(color = Color.White)

    ) {
        // Create references for the composables to constrain
        val (image, title, price, quantity, divider) = createRefs()

        Image(
            painter = rememberImagePainter(
                data = product.image
            ),
            contentDescription = product.title,
            modifier = Modifier
                .size(94.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = product.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Arapawa,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(image.end, margin = 8.dp)
                    end.linkTo(quantity.start, margin = 12.dp)
                    top.linkTo(parent.top, margin = 12.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = formatPrice(product.price),
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            color = Arapawa,
            modifier = Modifier
                .constrainAs(price) {
                    top.linkTo(title.bottom, margin = 4.dp)
                    start.linkTo(image.end, margin = 8.dp)
                    width = Dimension.fillToConstraints
                }
        )

        QuantitySelector(
            count = count,
            decreaseItemCount = { if (count > 1) updateCount(count - 1) },
            increaseItemCount = { updateCount(count + 1) },
            viewModel = viewModel,
            product = product,
            modifier = Modifier
                .constrainAs(quantity) {
                    top.linkTo(parent.top, margin = 12.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                }
        )

        Divider(
            color = Color.Black.copy(alpha = 0.15f),
            thickness = 1.dp,
            modifier = Modifier
                .constrainAs(divider) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(image.bottom, margin = 12.dp)
                })
    }

}