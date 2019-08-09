package com.hugoung.drivy.extension

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.hugoung.drivy.R

/**
 * Show an error snack
 */
fun View.snack(message: String) {
    val snack = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    if (context is Activity) {
        snack.view.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
        val tv = snack.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        tv.setTextColor(ContextCompat.getColor(context, R.color.white))
    } else throw IllegalStateException("View needs to be attached to an Activity.")
    snack.show()
}