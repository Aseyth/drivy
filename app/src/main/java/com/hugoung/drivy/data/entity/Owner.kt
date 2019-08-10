package com.hugoung.drivy.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    val name: String,
    val picture_url: String,
    val rating: Rating
) : Parcelable