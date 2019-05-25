package com.zain.fly365.flightsearch.domain

import com.zain.fly365.base.domain.usecases.UseCase
import com.zain.fly365.flightsearch.entities.Airport

class GetAirportsUseCase :
    UseCase<GetAirportsUseCase.Params, List<Airport>> {
    override fun execute(param: Params?): List<Airport> {
        return listOf(
            Airport("CAI", "Cairo International Airport"),
            Airport("SYD", "Sydney International Airport"),
            Airport("ASW", "Aswan International Airport"),
            Airport("JED", "Jeddah International Airport"),
            Airport("RUH", "Riyadh International Airport"),
            Airport("MED", "Madinah International Airport")
        )
    }

    class Params private constructor() {
        companion object
    }
}