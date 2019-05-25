package com.zain.fly365.flightsearch.entities


import com.google.gson.annotations.SerializedName

data class Segment(
    val baggage: String,
    val bookingInfo: BookingInfo,
    val cabinClass: String,
    val carrier: CarrierX,
    val destination: Destination,
    val flightInfo: FlightInfo,
    val fuellingStops: List<Any>,
    val origin: Origin,
    val seatCount: String,
    val segmentId: String,
    val stopOvertime: Int,
    val stops: List<Any>
)