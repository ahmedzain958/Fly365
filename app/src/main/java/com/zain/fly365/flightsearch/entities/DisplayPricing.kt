package com.zain.fly365.flightsearch.entities

data class DisplayPricing(
    val base: Int,
    val conversionRate: Int,
    val currencyCode: String,
    val perPassenger: PerPassenger,
    val tax: Double,
    val total: Double
)