package com.hugoung.drivy.data.entity

data class CarList(
    val brand: String,
    val model: String,
    val picture_url: String,
    val price_per_day: Int,
    val rating: Rating,
    val owner: Owner
)