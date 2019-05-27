package com.zain.fly365.flightsearch.data

enum class AirportsEnum(val index: Int, var value: String,val code: String) {

    CAIRO_AIRPORT(0, "Cairo International Airport","CAI"),
    DUBAI_AIRPORT(1, "Dubai","DXB"),
    KINGSFORD_AIRPORT(2, "Kingsford Smith","SYD"),
    SUVARNABHUMI_AIRPORT(3, "Suvarnabhumi International","BKK"),
    SINGAPORE_AIRPORT(4, "Singapore Changi","SIN");

    companion object {
        private val map = values().associateBy(AirportsEnum::index)
        fun fromInt(type: Int) = map[type]
    }

}