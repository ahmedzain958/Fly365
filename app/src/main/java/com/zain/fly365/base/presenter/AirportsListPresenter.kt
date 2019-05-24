package com.zain.fly365.base.presenter

import com.hogroup.uctd.base.presenter.BasePresenter
import com.zain.fly365.oneway.entities.Airport

interface AirportsListPresenter: BasePresenter {
    fun getAirportsList(): List<Airport>
}