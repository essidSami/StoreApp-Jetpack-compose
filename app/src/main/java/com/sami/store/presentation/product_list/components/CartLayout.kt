package com.sami.store.presentation.product_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sami.store.domaine.model.Product
import com.sami.store.presentation.Screen
import com.sami.store.presentation.ui.theme.Eminence
import com.sami.store.presentation.ui.theme.KeyLimePie
import com.sami.store.util.formatPrice

@Composable
fun CartLayout(
    navController: NavController,
    productList: List<Product>?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(28.dp)
            .background(shape = RoundedCornerShape(20.dp), color = Eminence)
            .clickable {
                navController.navigate(Screen.CartDetailsScreen.route)
            }
    ) {

        Text(
            text = productList?.map { it.quantity }?.sum().toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(start = 24.dp, top = 12.dp, bottom = 12.dp)
                .background(shape = RoundedCornerShape(10.dp), color = KeyLimePie)
                .padding(12.dp)
        )

        Text(
            text = "Cart",
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            modifier = Modifier
                .padding(start = 32.dp)
                .align(Alignment.CenterVertically)
                .weight(1f)
        )

        Text(
            text = formatPrice( productList?.map { it.price * it.quantity }?.sum()),
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            color = Color.White,
            modifier = Modifier
                .padding(end = 32.dp)
                .align(alignment = Alignment.CenterVertically)
        )
    }

}