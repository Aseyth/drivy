package com.hugoung.drivy.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hugoung.drivy.R
import com.hugoung.drivy.data.entity.CarDetails
import com.hugoung.drivy.extension.withParcelable
import kotlinx.android.synthetic.main.fragment_car_details.*

class CarDetailsFragment : Fragment() {

    companion object {

        private const val ARGUMENT_CAR = "ARGUMENT_CAR"

        fun createInstance(context: Context, carDetails: CarDetails): CarDetailsFragment {
            return instantiate(context, CarDetailsFragment::class.java.name).apply {
                withParcelable(ARGUMENT_CAR, carDetails)
            } as CarDetailsFragment
        }

        private fun extractFromIntent(fragment: CarDetailsFragment): CarDetails {
            return fragment.arguments!!.getParcelable(ARGUMENT_CAR) as CarDetails
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_car_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateData(extractFromIntent(this))
        backButton.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    private fun populateData(carDetails: CarDetails) {
        Glide
            .with(context!!)
            .load(carDetails.picture_url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(carDetailsPicture)

        carDetailsName.text = getString(R.string.car_name, carDetails.brand, carDetails.model)
        carDetailsPrice.text = getString(R.string.car_price, carDetails.price_per_day, getString(R.string.price))
        carRating.rating = carDetails.rating.average
        carRatingCount.text = getString(R.string.rating_count, carDetails.rating.count)


        Glide
            .with(context!!)
            .load(carDetails.owner.picture_url)
            .transform(CenterCrop(), RoundedCorners(6))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(carOwnerPicture)
        carOwnerName.text = carDetails.owner.name
        carOwnerRating.rating = carDetails.owner.rating.average
    }
}