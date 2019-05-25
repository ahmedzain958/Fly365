package com.zain.fly365.flightsearch.data

import com.zain.fly365.flightsearch.domain.FlightsSearchRepository
import io.reactivex.Completable

class FlightsSearchRepositoryImpl(private val flightsSearchLocalDataSource: FlightsSearchLocalDataSource) :
    FlightsSearchRepository {
    override fun insertSearchOptions(cabinClass: Int, adults: Int, children: Int, infants: Int): Completable {
       return flightsSearchLocalDataSource.insertSearchOptions(
            cabinClass, adults,
            children, infants
        )
    }

}