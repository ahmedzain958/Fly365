package com.zain.fly365.flightsearch.entities


import com.google.gson.annotations.SerializedName

data class ADT(
    val base: Int,
    val code: String,
    val count: Int,
    val tax: Double,
    val total: Double
)