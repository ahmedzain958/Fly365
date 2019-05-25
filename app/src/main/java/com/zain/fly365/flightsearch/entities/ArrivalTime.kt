package com.zain.fly365.flightsearch.entities


import com.google.gson.annotations.SerializedName

data class ArrivalTime(
    val date: String,
    val gds: String,
    val time: String,
    val zone: String
)