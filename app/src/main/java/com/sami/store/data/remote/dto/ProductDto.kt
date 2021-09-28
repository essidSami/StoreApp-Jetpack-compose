package com.sami.store.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sami.store.domaine.model.Product

data class ProductDto(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("rating")
    val rating: RatingDto,
    @SerializedName("title")
    val title: String
)

fun ProductDto.toProduct(): Product{
    return Product(
        category = category,
        description = description,
        id = id,
        image = image,
        price = price,
        ratingCount = rating.count,
        ratingRate = rating.rate,
        title = title
    )
}