package com.zain.fly365.flightsearch.domain

import com.zain.fly365.flightsearch.data.CabinClass
import io.reactivex.Completable

interface FlightsSearchRepository{
    fun insertSearchOptions(
        cabinClass: Int,
        adults: Int,
        children: Int,
        infants: Int
    ):Completable
    fun getTravellersNumber(): Int
    fun getAdultsNumber(): Int
    fun getChildrenNumber(): Int
    fun getInfantsNumber(): Int
    fun getCabinClass(): CabinClass
}