package com.zain.fly365.flightsearch.data

import com.zain.fly365.flightsearch.entities.Leg
import com.zain.fly365.flightsearch.entities.RequestLeg
import com.zain.fly365.flightsearch.entities.SearchResponse
import io.reactivex.Single


interface FlightsSearchRemoteDataSource {
    fun searchOneWayFlights(
        cabinClass: String, infant: Int, child: Int, adult: Int, legs: List<RequestLeg>
    ): Single<SearchResponse>
}