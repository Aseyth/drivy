package com.hugoung.drivy.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
    val average: Float,
    val count: Int
) : Parcelable