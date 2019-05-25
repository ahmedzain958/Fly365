package com.zain.fly365.flightsearch.entities

import java.io.Serializable

data class Leg(
    val cabinClass: String,
    val arrivalDate: String,
    val carrier: Carrier,
    val departureDate: String,
    val destination: String,
    val duration: Int,
    val legId: String,
    val origin: String,
    val segments: List<String>,
    val stops: Int
)