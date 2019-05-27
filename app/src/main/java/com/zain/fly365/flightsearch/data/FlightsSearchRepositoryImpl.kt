package com.zain.fly365.flightsearch.data

import com.zain.fly365.flightsearch.entities.RequestLeg
import com.zain.fly365.flightsearch.entities.SearchResponse
import io.reactivex.Completable
import io.reactivex.Single

class FlightsSearchRepositoryImpl(
    private val flightsSearchLocalDataSource: FlightsSearchLocalDataSource,
    private val flightsSearchRemoteDataSource: FlightsSearchRemoteDataSource
) :
    FlightsSearchRepository {

    override fun getCabinClass(): CabinClassEnum {
        return flightsSearchLocalDataSource.getCabinClass()
    }

    override fun getTravellersNumber(): Int {
        return flightsSearchLocalDataSource.getTravellersNumber()
    }

    override fun insertSearchOptions(cabinClass: Int, adults: Int, children: Int, infants: Int): Completable {
        return flightsSearchLocalDataSource.insertSearchOptions(
            cabinClass, adults,
            children, infants
        )
    }

    override fun getAdultsNumber(): Int {
        return flightsSearchLocalDataSource.getAdultsNumber()
    }

    override fun getChildrenNumber(): Int {
        return flightsSearchLocalDataSource.getChildrenNumber()
    }

    override fun getInfantsNumber(): Int {
        return flightsSearchLocalDataSource.getInfantsNumber()
    }

    override fun searchOneWayFlights(
        cabinClass: String, infant: Int, child: Int, adult: Int, legs: List<RequestLeg>
    ): Single<SearchResponse> {
        return flightsSearchRemoteDataSource.searchOneWayFlights(
            cabinClass, infant, child, adult, legs
        )
    }

}