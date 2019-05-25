package com.zain.fly365.flightsearch.domain

import com.zain.fly365.base.domain.usecases.UseCase
import com.zain.fly365.flightsearch.entities.Airport

class GetChildrenNumberUseCase(private val flightsSearchRepository: FlightsSearchRepository) :
    UseCase<GetChildrenNumberUseCase.Params, Int> {
    override fun execute(param: Params?): Int {
        return flightsSearchRepository.getChildrenNumber()
    }

    class Params private constructor() {
        companion object
    }
}