package com.zain.fly365.oneway.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.zain.fly365.R
import com.zain.fly365.flightsearch.entities.RequestLeg
import com.zain.fly365.oneway.presentation.presenter.OneWayFlightPresenter
import com.zain.fly365.oneway.presentation.presenter.OneWayFlightsView
import kotlinx.android.synthetic.main.activity_one_way_flights.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class OneWayFlightsActivity : AppCompatActivity(), OneWayFlightsView {
    companion object {
        const val LEG_KEY = "leg_key"
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
            //todo
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

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recyclerViewFlights.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        recyclerViewFlights.visibility = View.VISIBLE
    }

    override fun showError(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
