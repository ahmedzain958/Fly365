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
import com.zain.fly365.oneway.entities.Airport
import com.zain.fly365.flightsearch.presentation.ui.AirportsActivity
import com.zain.fly365.base.presenter.AirportsListPresenter
import com.zain.fly365.oneway.presentation.presenter.OneWayView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*


class OneWayFragment : Fragment(), OneWayView {

    companion object {
        private const val AIRPORTS_ORIGIN_CITY_REQUEST_CODE = 600
        private const val AIRPORTS_DESTINATION_CITY_REQUEST_CODE = 601
        private const val FIRST_AIRPORT_INDEX = 0
        private const val SECOND_AIRPORT_INDEX = 1
        private const val MONTH_DAY_DATE_FORMAT = "MMM dd"
        private const val DAY_DATE_FORMAT = "EEEE"
    }

    private lateinit var textViewOriginCity: TextView
    private lateinit var textViewOriginAirport: TextView
    private lateinit var textViewDestinationCity: TextView
    private lateinit var textViewDestinationAirport: TextView
    private lateinit var textViewDate: TextView
    private lateinit var textViewDay: TextView
    private lateinit var selectedOriginAirport: Airport
    private lateinit var selectedDestinationAirport: Airport
    private lateinit var airportsList: List<Airport>
    private val airportsListPresenter: AirportsListPresenter by inject { parametersOf(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one_way, container, false)
        airportsList = airportsListPresenter.getAirportsList()
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
        setOriginDestCardViewsListeners(view)
        setDateCardViewsListener(view)
        swapAirports(view)
    }

    private fun setDateCardViewsListener(view: View) {

        val dateCard = view.findViewById<CardView>(R.id.dateCard)
        val currentCalender = Calendar.getInstance()
        setDate(currentCalender)

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val selectedCalender = Calendar.getInstance()
            selectedCalender.set(Calendar.YEAR, year)
            selectedCalender.set(Calendar.MONTH, monthOfYear)
            selectedCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setDate(selectedCalender)
        }
        dateCard.setOnClickListener {
            context?.let {
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

    @SuppressLint("SimpleDateFormat")
    private fun setDate(calender: Calendar) {
        val todaysDate = SimpleDateFormat(MONTH_DAY_DATE_FORMAT).format(calender.time)
        val todaysDay = SimpleDateFormat(DAY_DATE_FORMAT).format(calender.time)
        textViewDate.setText(todaysDate)
        textViewDay.setText(todaysDay)
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
        textViewDate = view.findViewById<TextView>(R.id.textViewDate)
        textViewDay = view.findViewById<TextView>(R.id.textViewDay)
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
