package com.zain.fly365.flightsearch.presentation.presenter

import com.zain.fly365.flightsearch.domain.GetAirportsUseCase
import com.zain.fly365.flightsearch.entities.Airport

class AirportsListPresenterImpl constructor(
    private val getAirportsUseCase: GetAirportsUseCase
) : AirportsListPresenter {
    override fun getAirportsList(): List<Airport> {
        return getAirportsUseCase.execute(null)
    }
    override fun onDestroy() {
    }
}