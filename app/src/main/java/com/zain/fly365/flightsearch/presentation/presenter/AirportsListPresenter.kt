package com.zain.fly365.flightsearch.presentation.presenter

import com.zain.fly365.base.presenter.BasePresenter
import com.zain.fly365.oneway.entities.Airport

interface AirportsListPresenter: BasePresenter {
    fun getAirportsList(): List<Airport>
}