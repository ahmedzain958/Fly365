package com.zain.fly365.flightsearch.domain

import com.zain.fly365.base.domain.usecases.UseCase
import com.zain.fly365.flightsearch.data.CabinClassEnum
import com.zain.fly365.flightsearch.data.FlightsSearchRepository

class GetCabinClassUseCase(private val flightsSearchRepository: FlightsSearchRepository) :
    UseCase<GetCabinClassUseCase.Params, CabinClassEnum> {
    override fun execute(param: Params?): CabinClassEnum {
        return flightsSearchRepository.getCabinClass()
    }

    class Params private constructor() {
        companion object
    }
}