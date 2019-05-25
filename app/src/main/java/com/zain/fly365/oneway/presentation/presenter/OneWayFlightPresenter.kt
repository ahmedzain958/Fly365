package com.zain.fly365.oneway.presentation.presenter

import com.zain.fly365.base.presenter.BasePresenter
import com.zain.fly365.flightsearch.entities.RequestLeg

interface OneWayFlightPresenter: BasePresenter {
    fun getFlights(leg: RequestLeg)
}