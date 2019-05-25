package com.zain.fly365.flightsearch.entities

data class Itinerary(
    val airPriceGroup: Int,
    val bookingCodes: String,
    val carrier: CarrierXX,
    val displayPricing: DisplayPricing,
    val itineraryId: String,
    val legs: List<String>,
    val pricing: Pricing,
    val totalStops: Int
)