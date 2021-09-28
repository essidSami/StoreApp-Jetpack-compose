package com.sami.store.presentation.product_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.sami.store.R
import com.sami.store.domaine.model.Product
import com.sami.store.presentation.ui.theme.Arapawa
import com.sami.store.presentation.ui.theme.Eminence
import com.sami.store.presentation.ui.theme.KeyLimePie
import com.sami.store.util.formatPrice

@Composable
fun ProductListItem(
    navController: NavController,
    product: Product,
    onItemClick: (Product) -> Unit
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(shape = RoundedCornerShape(30.dp), color = Color.White)
            .clickable { onItemClick(product) }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(
                    data = product.image
                ),
                contentDescription = product.title,
                modifier = Modifier
                    .size(94.dp)
                    .padding(12.dp)
            )

            Column(
                modifier = Modifier
                    .padding(end = 12.dp)
            ) {
                Text(
                    text = product.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Arapawa,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = product.description,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = Arapawa,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Text(
                        text = formatPrice(product.price),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = KeyLimePie,
                        modifier = Modifier.weight(1f)
                    )

                    Image(
                        painter = painterResource(
                            id = R.drawable.img_shoping_cart
                        ),
                        contentDescription = "shopping cart",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

        }
    }
}