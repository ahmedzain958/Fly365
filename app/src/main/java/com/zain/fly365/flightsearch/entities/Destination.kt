package com.zain.fly365.flightsearch.entities


import com.google.gson.annotations.SerializedName

data class Destination(
    val arrivalTime: ArrivalTime,
    val code: String,
    val terminal: String
)