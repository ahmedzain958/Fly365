package com.zain.fly365.flightsearch.entities


import com.google.gson.annotations.SerializedName

data class Origin(
    val arrivalTime: ArrivalTime,
    val code: String,
    val departureTime: DepartureTime,
    val terminal: String
)