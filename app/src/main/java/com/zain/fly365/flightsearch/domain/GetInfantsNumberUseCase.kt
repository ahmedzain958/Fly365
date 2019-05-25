package com.zain.fly365.flightsearch.domain

import com.zain.fly365.base.domain.usecases.UseCase
import com.zain.fly365.flightsearch.data.FlightsSearchRepository

class GetInfantsNumberUseCase(private val flightsSearchRepository: FlightsSearchRepository) :
    UseCase<GetInfantsNumberUseCase.Params, Int> {
    override fun execute(param: Params?): Int {
        return flightsSearchRepository.getInfantsNumber()
    }

    class Params private constructor() {
        companion object
    }
}