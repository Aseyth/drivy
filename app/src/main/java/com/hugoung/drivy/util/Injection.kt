package com.hugoung.drivy.util

import com.hugoung.drivy.data.DrivyRepository
import com.hugoung.drivy.data.DrivyService

object Injection {

    /**
     * ¨Provides the drivy repository
     *
     * @return DrivyRepository
     */
    fun provideDrivyRepository(): DrivyRepository {
        return DrivyRepository.getInstance(DrivyService.getInstance())
    }

    /**
     * Provides scheduler provider
     *
     * @return SchedulerProvider
     */
    fun provideSchedulerProvider(): BaseSchedulerProvider =
        SchedulerProvider
}
