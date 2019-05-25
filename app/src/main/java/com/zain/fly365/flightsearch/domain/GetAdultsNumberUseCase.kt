package com.zain.fly365.flightsearch.domain

import com.zain.fly365.base.domain.usecases.UseCase
import com.zain.fly365.flightsearch.entities.Airport

class GetAdultsNumberUseCase(private val flightsSearchRepository: FlightsSearchRepository) :
    UseCase<GetAdultsNumberUseCase.Params, Int> {
    override fun execute(param: Params?): Int {
        return flightsSearchRepository.getAdultsNumber()
    }

    class Params private constructor() {
        companion object
    }
}