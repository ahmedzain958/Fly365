package com.zain.fly365.flightsearch.entities


import com.google.gson.annotations.SerializedName

data class Pricing(
    val base: Int,
    val currencyCode: String,
    val perPassenger: PerPassenger,
    val tax: Double,
    val total: Double
)