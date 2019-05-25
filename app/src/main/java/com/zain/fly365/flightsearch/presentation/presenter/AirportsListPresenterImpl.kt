package com.zain.fly365.flightsearch.presentation.presenter

import com.zain.fly365.oneway.domain.GetAirportsUseCase
import com.zain.fly365.oneway.entities.Airport

class AirportsListPresenterImpl constructor(
    private val getAirportsUseCase: GetAirportsUseCase
) : AirportsListPresenter {
    override fun getAirportsList(): List<Airport> {
        return getAirportsUseCase.execute(null)
    }
    override fun onDestroy() {
    }
}