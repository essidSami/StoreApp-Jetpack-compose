package com.sami.store.domaine.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    val category: String,
    val description: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val price: Double,
    val ratingCount: Int,
    val ratingRate: Double,
    val title: String,
    var quantity: Int = 1
)