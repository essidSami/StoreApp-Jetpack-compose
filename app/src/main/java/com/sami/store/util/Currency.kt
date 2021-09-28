package com.sami.store.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

fun formatPrice(price: Double?): String {
    return NumberFormat.getCurrencyInstance(Locale("en", "US")).format(
        BigDecimal(price ?: 0.0).setScale(2, RoundingMode.HALF_EVEN)
    )
}