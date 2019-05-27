package com.zain.fly365.flightsearch.data

import io.reactivex.Completable

interface FlightsSearchLocalDataSource {
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

}