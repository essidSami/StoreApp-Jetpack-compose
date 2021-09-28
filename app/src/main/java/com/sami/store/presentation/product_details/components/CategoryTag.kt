package com.sami.store.presentation.product_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sami.store.presentation.ui.theme.BlueRibbon
import com.sami.store.presentation.ui.theme.BurntSienna
import com.sami.store.presentation.ui.theme.Heliotrope
import com.sami.store.presentation.ui.theme.PastelGreen

@Composable
fun CategoryTag(category: String) {
    var color = BlueRibbon
    when (category) {
        "electronics" -> {
            color = Heliotrope
        }
        "jewelery" -> {
            color = PastelGreen
        }
        "men's clothing" -> {
            color = BlueRibbon
        }
        "women's clothing" -> {
            color = BurntSienna
        }
    }
    ChipView(category = category, color = color)
}

@Composable
fun ChipView(category: String, color: Color) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(.08f))
    ) {
        Text(
            text = category, modifier = Modifier.padding(12.dp, 6.dp, 12.dp, 6.dp),
            style = MaterialTheme.typography.caption,
            color = color
        )
    }
}