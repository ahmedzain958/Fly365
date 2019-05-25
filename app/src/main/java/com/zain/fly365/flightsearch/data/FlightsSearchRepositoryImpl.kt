package com.zain.fly365.flightsearch.data

import com.zain.fly365.flightsearch.domain.FlightsSearchRepository
import io.reactivex.Completable

class FlightsSearchRepositoryImpl(private val flightsSearchLocalDataSource: FlightsSearchLocalDataSource) :
    FlightsSearchRepository {


    override fun getCabinClass(): CabinClass {
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
}