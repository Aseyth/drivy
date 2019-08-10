package com.hugoung.drivy.navigation

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.hugoung.drivy.R
import com.hugoung.drivy.data.entity.CarDetails
import com.hugoung.drivy.extension.replace
import com.hugoung.drivy.ui.CarDetailsFragment
import com.hugoung.drivy.ui.CarListFragment

class DrivyNavigator(
    private val context: Context
) {

    /**
     * Present the car list fragment
     */
    fun presentCarListFragment(fragmentManager: FragmentManager) {
        val fragment = CarListFragment()
        fragmentManager.replace(fragment, R.id.root_frame, false)
    }

    /**
     * Present the car details fragment
     */
    fun presentCarDetailsFragment(fragmentManager: FragmentManager, carDetails: CarDetails) {
        val fragment = CarDetailsFragment.createInstance(context, carDetails)
        fragmentManager.replace(fragment, R.id.root_frame, true)
    }
}