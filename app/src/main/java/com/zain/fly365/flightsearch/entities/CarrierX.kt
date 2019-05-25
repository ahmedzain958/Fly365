package com.zain.fly365.flightsearch.entities


import com.google.gson.annotations.SerializedName

data class CarrierX(
    val aircraft: Aircraft,
    val code: String,
    val codeShare: Any,
    val flightNumber: String,
    val name: String
)