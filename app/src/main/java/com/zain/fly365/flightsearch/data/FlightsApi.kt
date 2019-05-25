package com.zain.fly365.flightsearch.data

import com.zain.fly365.flightsearch.entities.Leg
import com.zain.fly365.flightsearch.entities.RequestLeg
import com.zain.fly365.flightsearch.entities.SearchResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface FlightsApi {
    @POST("flight/search")
    fun searchOneWayFlights(@Body searchRequestBody: SearchrequestBody): Single<SearchResponse>
}

data class SearchrequestBody(
    val cabinClass: String, val infant: Int, val child: Int, val adult: Int,
    val legs: List<RequestLeg>
)