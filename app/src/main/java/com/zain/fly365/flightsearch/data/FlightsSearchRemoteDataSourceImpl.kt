package com.zain.fly365.flightsearch.data

import com.zain.fly365.flightsearch.entities.RequestLeg
import com.zain.fly365.flightsearch.entities.SearchResponse
import io.reactivex.Single

class FlightsSearchRemoteDataSourceImpl(val flightsApi: FlightsApi) : FlightsSearchRemoteDataSource {
    override fun searchOneWayFlights(
        cabinClass: String, infant: Int, child: Int, adult: Int, legs: List<RequestLeg>
    ): Single<SearchResponse> {
        return flightsApi.searchOneWayFlights(
            SearchrequestBody(
                cabinClass, infant, child, adult, legs
            )
        )
    }
}