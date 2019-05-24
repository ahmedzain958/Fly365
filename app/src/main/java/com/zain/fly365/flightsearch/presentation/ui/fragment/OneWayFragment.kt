package com.zain.fly365.flightsearch.presentation.ui.fragment


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

import com.zain.fly365.R
import com.zain.fly365.flightsearch.data.Airport
import com.zain.fly365.flightsearch.data.airportsList
import com.zain.fly365.flightsearch.presentation.ui.activity.AirportsActivity


class OneWayFragment : Fragment() {
    companion object {
        private const val AIRPORTS_ORIGIN_CITY_REQUEST_CODE = 600
        private const val AIRPORTS_DESTINATION_CITY_REQUEST_CODE = 601
        private const val FIRST_AIRPORT_INDEX = 0
        private const val SECOND_AIRPORT_INDEX = 1
    }

    private lateinit var textViewOriginCity: TextView
    private lateinit var textViewOriginAirport: TextView
    private lateinit var textViewDestinationCity: TextView
    private lateinit var textViewDestinationAirport: TextView
    private lateinit var imageViewSwap: ImageView
    private lateinit var selectedOriginAirport: Airport
    private lateinit var selectedDestinationAirport: Airport

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one_way, container, false)
        view?.let {
            initUI(view)
        }
        selectedOriginAirport = airportsList.get(FIRST_AIRPORT_INDEX)
        selectedDestinationAirport = airportsList.get(SECOND_AIRPORT_INDEX)
        return view
    }

    private fun initUI(view: View) {
        initViews(view)
        initViewsDefaultValues()
        setCardViewsListeners(view)
        swapAirports(view)
    }

    private fun swapAirports(view: View) {
        val imageViewSwap = view.findViewById<ImageView>(R.id.imageViewSwap)
        imageViewSwap.setOnClickListener {
            //swap origin to destination
            val temp = selectedOriginAirport
            selectedOriginAirport = selectedDestinationAirport
            selectedDestinationAirport = temp

            //swap origin to destination textviews
            textViewOriginCity.setText(selectedOriginAirport.city)
            textViewOriginAirport.setText(selectedOriginAirport.name)
            textViewDestinationCity.setText(selectedDestinationAirport.city)
            textViewDestinationAirport.setText(selectedDestinationAirport.name)
        }
    }

    private fun setCardViewsListeners(view: View) {
        val cityOriginCard = view.findViewById<CardView>(R.id.cityOriginCard)
        val cityDestinationCard = view.findViewById<CardView>(R.id.cityDestinationCard)
        cityOriginCard.setOnClickListener {
            Intent(context, AirportsActivity::class.java).also { intent ->
                startActivityForResult(intent, AIRPORTS_ORIGIN_CITY_REQUEST_CODE)
            }
        }
        cityDestinationCard.setOnClickListener {
            Intent(context, AirportsActivity::class.java)
                .also { intent ->
                    startActivityForResult(intent, AIRPORTS_DESTINATION_CITY_REQUEST_CODE)
                }
        }
    }

    private fun initViews(view: View) {
        textViewOriginCity = view.findViewById(R.id.textViewOriginCity)
        textViewOriginAirport = view.findViewById(R.id.textViewOriginAirport)
        textViewDestinationCity = view.findViewById(R.id.textViewDestinationCity)
        textViewDestinationAirport = view.findViewById(R.id.textViewDestinationAirport)
    }

    /*
        initialize origin & destination airports by default values
     */
    private fun initViewsDefaultValues() {
        textViewOriginCity.setText(airportsList.get(FIRST_AIRPORT_INDEX).city)
        textViewOriginAirport.setText(airportsList.get(FIRST_AIRPORT_INDEX).name)
        textViewDestinationCity.setText(airportsList.get(SECOND_AIRPORT_INDEX).city)
        textViewDestinationAirport.setText(airportsList.get(SECOND_AIRPORT_INDEX).name)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            val airport = data.getSerializableExtra(AirportsActivity.SELECTED_AIRPORT_KEY) as Airport
            if (resultCode == RESULT_OK) {
                if (requestCode == AIRPORTS_ORIGIN_CITY_REQUEST_CODE) {
                    validateSelectedCityFrom(airport)
                } else if (requestCode == AIRPORTS_DESTINATION_CITY_REQUEST_CODE) {
                    validateSelectedCityTo(airport)
                }
            }
        }
    }

    /*
        differentiate between origin and destination airports
    */
    private fun validateSelectedCityFrom(airport: Airport) {
        if (!selectedDestinationAirport.city.equals(airport.city)) {
            selectedOriginAirport = airport
            textViewOriginCity.setText(airport.city)
            textViewOriginAirport.setText(airport.name)
        } else {
            Toast.makeText(
                context,
                getString(R.string.origin_and_destination_should_be_different),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /*
         differentiate between origin and destination airports
    */
    private fun validateSelectedCityTo(airport: Airport) {
        if (!selectedOriginAirport.city.equals(airport.city)) {
            selectedDestinationAirport = airport
            textViewDestinationCity.setText(airport.city)
            textViewDestinationAirport.setText(airport.name)
        } else {
            Toast.makeText(
                context,
                getString(R.string.origin_and_destination_should_be_different),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
