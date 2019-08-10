package com.hugoung.drivy.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarDetails(
    val brand: String,
    val model: String,
    val picture_url: String,
    val price_per_day: Int,
    val rating: Rating,
    val owner: Owner
) : Parcelable