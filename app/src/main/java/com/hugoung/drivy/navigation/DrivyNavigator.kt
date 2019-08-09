package com.hugoung.drivy.navigation

import androidx.fragment.app.FragmentManager
import com.hugoung.drivy.R
import com.hugoung.drivy.extension.replace
import com.hugoung.drivy.ui.CarListFragment

class DrivyNavigator() {

    /**
     * Present the car list fragment
     */
    fun presentCarListFragment(fragmentManager: FragmentManager) {
        val fragment = CarListFragment()
        fragmentManager.replace(fragment, R.id.root_frame, false)
    }
}