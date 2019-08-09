package com.hugoung.drivy.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hugoung.drivy.ui.DrivyViewModel
import java.lang.IllegalArgumentException

/**
 * ViewModel factory
 *
 * Create ViewModels while injecting required dependencies
 */
class ViewModelFactory : ViewModelProvider.Factory {

    /**
     * @inheritDoc
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            DrivyViewModel::class.java -> {
                return DrivyViewModel(
                    Injection.provideDrivyRepository(),
                    Injection.provideSchedulerProvider()
                ) as T
            }
        }
        throw  IllegalArgumentException("Unknown model class $modelClass")
    }
}