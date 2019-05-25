package com.zain.fly365.flightsearch.entities


import com.google.gson.annotations.SerializedName

data class SearchParams(
    val adult: Int,
    val cabinClass: String,
    val child: Int,
    val infant: Int,
    val legs: List<Leg>
)