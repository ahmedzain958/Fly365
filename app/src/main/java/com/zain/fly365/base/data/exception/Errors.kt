package com.zain.fly365.base.data.exception

import com.google.gson.annotations.SerializedName

data class Errors(
    @SerializedName("legs.0.origin")
    val legsOrigin: List<String>?,
    @SerializedName("legs.0.destination")
    val legsDestination: List<String>?,
    @SerializedName("legs.0.departureDate")
    val legsDepartureDate: List<String>?
)