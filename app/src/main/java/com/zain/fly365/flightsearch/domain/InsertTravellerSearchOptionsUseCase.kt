package com.zain.fly365.flightsearch.domain

import com.zain.fly365.base.domain.usecases.UseCase
import com.zain.fly365.flightsearch.data.FlightsSearchRepository
import io.reactivex.Completable

class InsertTravellerSearchOptionsUseCase(private val flightsSearchRepository: FlightsSearchRepository) :
    UseCase<InsertTravellerSearchOptionsUseCase.Params, Completable> {
    override fun execute(param: Params?): Completable {
       return flightsSearchRepository.insertSearchOptions(param!!.cabinClass, param.adults, param.children, param.infants)
    }

    data class Params private constructor(
        val cabinClass: Int, val adults: Int,
        val children: Int, val infants: Int
    ) {
        companion object {
            @JvmStatic
            fun create(
                cabinClass: Int, adults: Int,
                children: Int, infants: Int
            ): Params {
                return Params(
                    cabinClass, adults,
                    children, infants
                )
            }
        }
    }
}