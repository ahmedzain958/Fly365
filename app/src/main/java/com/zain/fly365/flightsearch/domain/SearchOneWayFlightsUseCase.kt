package com.zain.fly365.flightsearch.domain

import com.zain.fly365.base.domain.usecases.UseCase
import com.zain.fly365.flightsearch.data.FlightsSearchRepository
import com.zain.fly365.flightsearch.entities.Leg
import com.zain.fly365.flightsearch.entities.RequestLeg
import com.zain.fly365.flightsearch.entities.SearchResponse
import io.reactivex.Single

class SearchOneWayFlightsUseCase(private val flightsSearchRepository: FlightsSearchRepository) :
    UseCase<SearchOneWayFlightsUseCase.Params, Single<SearchResponse>> {

    override fun execute(param: Params?): Single<SearchResponse> {
        return flightsSearchRepository.searchOneWayFlights(
            param!!.cabinClass,
            param.infant,
            param.child, param.adult,
            param.legs
        )
    }

    data class Params private constructor(
        val cabinClass: String, val infant: Int, val child: Int, val adult: Int, val legs: List<RequestLeg>
    ) {
        companion object {
            @JvmStatic
            fun create(
                cabinClass: String, infant: Int, child: Int, adult: Int, legs: List<RequestLeg>

            ): Params {
                return Params(
                    cabinClass, infant,
                    child, adult,
                    legs
                )
            }
        }
    }

}