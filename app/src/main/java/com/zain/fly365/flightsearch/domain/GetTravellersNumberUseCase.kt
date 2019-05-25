package com.zain.fly365.flightsearch.domain

import com.zain.fly365.base.domain.usecases.UseCase
import com.zain.fly365.flightsearch.data.FlightsSearchRepository

class GetTravellersNumberUseCase(private val flightsSearchRepository: FlightsSearchRepository) :
    UseCase<GetTravellersNumberUseCase.Params, Int> {
    override fun execute(param: Params?): Int {
        return flightsSearchRepository.getTravellersNumber()
    }

    class Params private constructor() {
        companion object
    }
}