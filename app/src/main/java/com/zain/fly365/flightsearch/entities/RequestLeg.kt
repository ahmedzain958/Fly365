package com.zain.fly365.flightsearch.entities

import java.io.Serializable

data class RequestLeg(var cabinClass: String,
                 var departureDate: String,
                 var destination: String,
                 var origin: String): Serializable