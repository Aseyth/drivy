package com.hugoung.drivy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hugoung.drivy.R
import com.hugoung.drivy.navigation.DrivyNavigator

class MainActivity : AppCompatActivity() {

    private val navigator: DrivyNavigator by lazy { DrivyNavigator(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.presentCarListFragment(supportFragmentManager)
    }
}
