package com.hugoung.drivy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hugoung.drivy.R
import com.hugoung.drivy.data.entity.CarDetails
import com.hugoung.drivy.extension.snack
import com.hugoung.drivy.navigation.DrivyNavigator
import com.hugoung.drivy.util.Event
import com.hugoung.drivy.util.ViewModelFactory
import kotlinx.android.synthetic.main.cell_cards.view.*
import kotlinx.android.synthetic.main.fragment_car_list.*

class CarListFragment : Fragment() {

    private val navigator: DrivyNavigator by lazy { DrivyNavigator(context!!) }

    private fun <T> LiveData<T>.observe(observe: (T) -> Unit) = observe(this@CarListFragment, Observer { observe(it) })

    private val drivyViewModel: DrivyViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory()).get(DrivyViewModel::class.java)
    }

    private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carPicture: ImageView = itemView.carPicture
        val carName: TextView = itemView.carName
        val price: TextView = itemView.carPrice
        val rating: RatingBar = itemView.ratingBar
        val ratingCount: TextView = itemView.ratingCount
    }

    private var adapter = RecyclerViewSimpleAdapter<CarDetails, ViewHolder>({ parent, _ ->
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_cards, parent, false))
    }, { holder, carDetails ->
        holder.carName.text = getString(R.string.car_name, carDetails.brand, carDetails.model)
        holder.price.text = getString(R.string.car_price, carDetails.price_per_day, getString(R.string.price))
        holder.rating.rating = carDetails.rating.average
        holder.ratingCount.text = getString(R.string.rating_count, carDetails.rating.count)
        Glide
            .with(context!!)
            .load(carDetails.picture_url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(holder.carPicture)
    }, {
        fragmentManager?.run {
            navigator.presentCarDetailsFragment(this, it)
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_car_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carRecycler.layoutManager = LinearLayoutManager(context!!)
        initObservers()
        if(drivyViewModel.savedList.isEmpty()) {
            drivyViewModel.fetch()
        } else {
            adapter.items = drivyViewModel.savedList
            carRecycler.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        drivyViewModel.dispose()
    }

    private fun initObservers() {
        drivyViewModel.carList.observe {
            it.getContentIfNotHandled()?.run {
                drivyViewModel.savedList = this
                adapter.items = this
                carRecycler.adapter = adapter
            }
        }
        drivyViewModel.errorResponse.observe {
            it.getContentIfNotHandled()?.run {
                carRecycler.snack(it.peekContent().message!!)
            }
        }
    }
}