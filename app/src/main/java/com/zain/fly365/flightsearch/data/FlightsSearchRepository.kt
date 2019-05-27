package com.zain.fly365.flightsearch.data

import com.zain.fly365.flightsearch.entities.RequestLeg
import com.zain.fly365.flightsearch.entities.SearchResponse
import io.reactivex.Completable
import io.reactivex.Single

interface FlightsSearchRepository {
    fun insertSearchOptions(
        cabinClass: Int,
        adults: Int,
        children: Int,
        infants: Int
    ): Completable

    fun getTravellersNumber(): Int
    fun getAdultsNumber(): Int
    fun getChildrenNumber(): Int
    fun getInfantsNumber(): Int
    fun getCabinClass(): CabinClassEnum
    fun searchOneWayFlights(
        cabinClass: String, infant: Int, child: Int, adult: Int, legs: List<RequestLeg>
    ): Single<SearchResponse>
}