package com.hugoung.drivy.data

import com.hugoung.drivy.data.entity.CarDetails
import com.hugoung.drivy.util.SingletonHolderSingleArg
import io.reactivex.Single

class DrivyRepository(
    private val service: DrivyService
) {

    companion object : SingletonHolderSingleArg<DrivyRepository, DrivyService>(::DrivyRepository)

    fun loadUrl(url: String): Single<ArrayList<CarDetails>> {
        return service.getUrl(url)
    }
}