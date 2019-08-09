package com.hugoung.drivy.data

import com.hugoung.drivy.data.entity.CarList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface DrivyService  {

    companion object {
        fun getInstance(): DrivyService {
            return RetrofitConfiguration.create().create(DrivyService::class.java)
        }
    }

    @GET
    fun getUrl(@Url url: String): Single<ArrayList<CarList>>
}