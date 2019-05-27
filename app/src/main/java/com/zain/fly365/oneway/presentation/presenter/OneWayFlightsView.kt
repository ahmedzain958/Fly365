package com.zain.fly365.oneway.presentation.presenter

import com.zain.fly365.base.presenter.BaseView
import com.zain.fly365.flightsearch.entities.Flight

interface OneWayFlightsView : BaseView {
    fun fillFlightsList(flights: List<Flight>)

}