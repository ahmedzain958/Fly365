package com.zain.fly365.oneway.presentation.ui

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
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
import com.zain.fly365.flightsearch.data.DateConstants
import com.zain.fly365.flightsearch.entities.Airport
import com.zain.fly365.flightsearch.presentation.ui.AirportsActivity
import com.zain.fly365.flightsearch.presentation.presenter.AirportsListPresenter
import com.zain.fly365.flightsearch.data.CabinClass
import com.zain.fly365.flightsearch.presentation.presenter.SearchOptionsPresenter
import com.zain.fly365.flightsearch.presentation.ui.SearchOptionsActivity
import com.zain.fly365.oneway.presentation.presenter.OneWayView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*

class OneWayFragment : Fragment(), OneWayView {

    companion object {
        private const val AIRPORTS_ORIGIN_CITY_REQUEST_CODE = 600
        private const val AIRPORTS_DESTINATION_CITY_REQUEST_CODE = 601
        private const val SEARCH_OPTIONS_REQUEST_CODE = 602
        private const val FIRST_AIRPORT_INDEX = 0
        private const val SECOND_AIRPORT_INDEX = 1
    }

    private lateinit var textViewOriginCity: TextView
    private lateinit var textViewOriginAirport: TextView
    private lateinit var textViewDestinationCity: TextView
    private lateinit var textViewDestinationAirport: TextView
    private lateinit var textViewDate: TextView
    private lateinit var textViewDay: TextView
    private lateinit var textViewTravellers: TextView
    private lateinit var textViewCabinClass: TextView
    private lateinit var selectedOriginAirport: Airport
    private lateinit var selectedDestinationAirport: Airport
    private lateinit var airportsList: List<Airport>
    private lateinit var departureDate: String
    private lateinit var cabinClass: CabinClass
    private val airportsListPresenter: AirportsListPresenter by inject { parametersOf(this) }
    private val searchOptionsPresenter: SearchOptionsPresenter by inject { parametersOf(this) }
    private var travellersNumber = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one_way, container, false)
        airportsList = airportsListPresenter.getAirportsList()
        view?.let {
            initUI(view)
        }
        initDefaultValues()
        return view
    }

    /*
        init class variables values
    */
    private fun initDefaultValues() {
        selectedOriginAirport = airportsList.get(FIRST_AIRPORT_INDEX)
        selectedDestinationAirport = airportsList.get(SECOND_AIRPORT_INDEX)
    }

    private fun initUI(view: View) {
        initViews(view)
        initViewsDefaultValues()
        setOriginDestCardViewsListeners(view)
        setDateCardViewListener(view)
        setSearchOptionsCardViewListener(view)
        swapAirports(view)
    }

    private fun setDateCardViewListener(view: View) {
        val dateCard = view.findViewById<CardView>(R.id.dateCard)
        val currentCalender = Calendar.getInstance()
        setDate(currentCalender)

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            val selectedCalender = Calendar.getInstance()
            selectedCalender.set(Calendar.YEAR, year)
            selectedCalender.set(Calendar.MONTH, monthOfYear)
            selectedCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setDate(selectedCalender)
        }
        dateCard.setOnClickListener {
            context?.let { context ->
                val datePickerDialog =
                    DatePickerDialog(
                        context, dateSetListener,
                        currentCalender.get(Calendar.YEAR),
                        currentCalender.get(Calendar.MONTH),
                        currentCalender.get(Calendar.DAY_OF_MONTH)
                    )
                datePickerDialog.datePicker.minDate = currentCalender.getTime().getTime()
                datePickerDialog.show()
            }

        }
    }

    private fun setSearchOptionsCardViewListener(view: View) {
        val searchOptionsCard = view.findViewById<CardView>(R.id.searchOptionsCard)
        textViewTravellers = view.findViewById(R.id.textViewTravellers)
        textViewCabinClass = view.findViewById(R.id.textViewCabinClass)
        setTravellersNumber()
        textViewCabinClass.text = searchOptionsPresenter.getSelectedCabinClass().name
        searchOptionsCard.setOnClickListener {
            Intent(context, SearchOptionsActivity::class.java).also { intent ->
                startActivityForResult(
                    intent,
                    SEARCH_OPTIONS_REQUEST_CODE
                )
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setDate(calender: Calendar) {
        val todaysDate = SimpleDateFormat(DateConstants.MONTH_DAY_DATE_FORMAT).format(calender.time)
        val todaysDay = SimpleDateFormat(DateConstants.DAY_DATE_FORMAT).format(calender.time)
        textViewDate.text = todaysDate
        textViewDay.text = todaysDay
        departureDate = SimpleDateFormat(DateConstants.DEPARTURE_DATE_FORMAT).format(calender.time)
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

    private fun setOriginDestCardViewsListeners(view: View) {
        val cityOriginCard = view.findViewById<CardView>(R.id.cityOriginCard)
        val cityDestinationCard = view.findViewById<CardView>(R.id.cityDestinationCard)
        cityOriginCard.setOnClickListener {
            Intent(context, AirportsActivity::class.java).also { intent ->
                startActivityForResult(
                    intent,
                    AIRPORTS_ORIGIN_CITY_REQUEST_CODE
                )
            }
        }
        cityDestinationCard.setOnClickListener {
            Intent(context, AirportsActivity::class.java)
                .also { intent ->
                    startActivityForResult(
                        intent,
                        AIRPORTS_DESTINATION_CITY_REQUEST_CODE
                    )
                }
        }
    }

    private fun initViews(view: View) {
        textViewOriginCity = view.findViewById(R.id.textViewOriginCity)
        textViewOriginAirport = view.findViewById(R.id.textViewOriginAirport)
        textViewDestinationCity = view.findViewById(R.id.textViewDestinationCity)
        textViewDestinationAirport = view.findViewById(R.id.textViewDestinationAirport)
        textViewDate = view.findViewById(R.id.textViewDate)
        textViewDay = view.findViewById(R.id.textViewDay)
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
            if (resultCode == RESULT_OK) {
                if (requestCode == AIRPORTS_ORIGIN_CITY_REQUEST_CODE) {
                    val airport = data.getSerializableExtra(AirportsActivity.SELECTED_AIRPORT_KEY) as Airport
                    validateSelectedCityFrom(airport)
                } else if (requestCode == AIRPORTS_DESTINATION_CITY_REQUEST_CODE) {
                    val airport = data.getSerializableExtra(AirportsActivity.SELECTED_AIRPORT_KEY) as Airport
                    validateSelectedCityTo(airport)
                } else if (requestCode == SEARCH_OPTIONS_REQUEST_CODE) {
                    setTravellersNumber()
                    textViewCabinClass.text = searchOptionsPresenter.getSelectedCabinClass().name
                }
            }
        }
    }

    private fun setTravellersNumber() {
        travellersNumber = searchOptionsPresenter.getTravellersNumber()
        textViewTravellers.text =
            String.format(
                "%s %s", travellersNumber, if (travellersNumber > 1) getString(R.string.travellers)
                else getString(R.string.traveller)
            )
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


    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
