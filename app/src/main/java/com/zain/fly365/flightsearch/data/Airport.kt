package com.zain.fly365.flightsearch.data

import java.io.Serializable

data class Airport(val city: String, val name: String):Serializable
// dummy list of airports instead of adding all world airports
 val airportsList = listOf(
    Airport("CAI", "Cairo International Airport"),
    Airport("SYD", "Sydney International Airport"),
    Airport("ASW", "Aswan International Airport"),
    Airport("JED", "Jeddah International Airport"),
    Airport("RUH", "Riyadh International Airport"),
    Airport("MED", "Madinah International Airport")
)
