package com.zain.fly365.flightsearch.domain

import io.reactivex.Completable

interface FlightsSearchRepository{
    fun insertSearchOptions(
        cabinClass: Int,
        adults: Int,
        children: Int,
        infants: Int
    ):Completable
}