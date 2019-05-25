package com.zain.fly365.flightsearch.entities


import com.google.gson.annotations.SerializedName

data class PerPassenger(
    @SerializedName("ADT")
    val aDT: ADT
)