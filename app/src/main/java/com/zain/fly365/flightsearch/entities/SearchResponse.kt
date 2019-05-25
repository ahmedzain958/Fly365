package com.zain.fly365.flightsearch.entities


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val airports: List<AirportX>,
    val itineraries: List<Itinerary>,
    val legs: List<Leg>,
    val searchParams: SearchParams,
    val segments: List<Segment>
)