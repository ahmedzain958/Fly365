package com.zain.fly365.flightsearch.entities


import com.google.gson.annotations.SerializedName

data class BookingInfo(
    val bookingCount: String,
    val cabinClass: String,
    val cabinCode: String,
    val fareBasis: String
)