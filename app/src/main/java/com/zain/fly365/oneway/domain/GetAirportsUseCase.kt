package com.zain.fly365.oneway.domain

import com.zain.fly365.base.domain.usecases.UseCase
import com.zain.fly365.oneway.entities.Airport
import io.reactivex.Single

class GetAirportsUseCase(private val oneWayRepository: OneWayRepository) :
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
        companion object {
            @JvmStatic
            fun create(): Params {
                return Params()
            }
        }
    }
}