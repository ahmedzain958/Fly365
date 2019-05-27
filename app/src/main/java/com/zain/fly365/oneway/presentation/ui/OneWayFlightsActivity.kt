package com.zain.fly365.oneway.presentation.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.zain.fly365.R
import com.zain.fly365.flightsearch.entities.Airport
import com.zain.fly365.flightsearch.entities.Flight
import com.zain.fly365.flightsearch.entities.RequestLeg
import com.zain.fly365.flightsearch.presentation.ui.AirportsActivity
import com.zain.fly365.flightsearch.presentation.ui.FilterActivity
import com.zain.fly365.flightsearch.presentation.ui.FilterActivity.Companion.SELECTED_AIRPORTS_KEY
import com.zain.fly365.flightsearch.presentation.ui.FilterActivity.Companion.SELECTED_AIRWAYS_KEY
import com.zain.fly365.flightsearch.presentation.ui.FilterActivity.Companion.SELECTED_STOPS_KEY
import com.zain.fly365.flightsearch.presentation.ui.adapter.AirportsAdapter
import com.zain.fly365.oneway.presentation.presenter.OneWayFlightPresenter
import com.zain.fly365.oneway.presentation.presenter.OneWayFlightsView
import kotlinx.android.synthetic.main.activity_airports.*
import kotlinx.android.synthetic.main.activity_one_way_flights.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class OneWayFlightsActivity : AppCompatActivity(), OneWayFlightsView {

    companion object {
        const val LEG_KEY = "leg_key"
        const val FILTER_REQUEST_CODE = 700
    }

    private val oneWayFlightPresenter: OneWayFlightPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_way_flights)
        initUI()
        val leg = intent.getSerializableExtra(LEG_KEY) as RequestLeg
        oneWayFlightPresenter.getFlights(leg)

    }

    private fun initUI() {
        initilizeToolbar()
        fabFilter.setOnClickListener {
            startActivityForResult(
                Intent(this, FilterActivity::class.java), FILTER_REQUEST_CODE
            )
        }
    }

    private fun initilizeToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true);
            setDisplayShowHomeEnabled(true);
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun fillFlightsList(flights: List<Flight>) {
        val adapter = OneWayFlightsAdapter(flights)
        recyclerViewFlights.layoutManager = LinearLayoutManager(this)
        recyclerViewFlights.setAdapter(adapter)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recyclerViewFlights.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        recyclerViewFlights.visibility = View.VISIBLE
    }

    override fun showError(error: String) {
        Toast.makeText(
            this,
            error,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showMessage(msg: String) {
        Toast.makeText(
            this,
            msg,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == SELECTED_AIRWAYS_KEY) {

                } else if (requestCode == SELECTED_AIRPORTS_KEY) {
                } else if (requestCode == SELECTED_STOPS_KEY) {
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}
