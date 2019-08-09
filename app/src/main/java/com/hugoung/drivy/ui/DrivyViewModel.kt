package com.hugoung.drivy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hugoung.drivy.data.DrivyRepository
import com.hugoung.drivy.data.entity.CarList
import com.hugoung.drivy.util.BaseSchedulerProvider
import com.hugoung.drivy.util.Event
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class DrivyViewModel(
    private val repository: DrivyRepository,
    private val scheduler: BaseSchedulerProvider
) : ViewModel() {

    /**
     * Disposable bag
     */
    private val disposables = CompositeDisposable()

    /**
     * LiveData that would be notified of server responses when fetching car list
     */
    private var mutableCarListResponse = MutableLiveData<Event<ArrayList<CarList>>>()

    /**
     * LiveData exposed to Views to listen when fetching car list
     */
    val carList: LiveData<Event<ArrayList<CarList>>> = mutableCarListResponse

    private var mutableErrorResponse = MutableLiveData<Event<Throwable>>()

    val errorResponse: LiveData<Event<Throwable>> = mutableErrorResponse

    /**
     * Fetch the car list
     */
    fun fetch() {
        disposables += repository.loadUrl("https://raw.githubusercontent.com/drivy/jobs/master/android/api/cars.json")
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe( {
                mutableCarListResponse.postValue(Event(it))
            }, {
              mutableErrorResponse.postValue(Event(it))
            })
    }

    /**
     * Dispose
     */
    fun dispose() {
        disposables.dispose()
    }
}