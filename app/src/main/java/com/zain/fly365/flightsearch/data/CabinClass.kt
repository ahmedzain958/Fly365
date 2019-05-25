package com.zain.fly365.flightsearch.data

enum class CabinClass(val value: Int, var isSelected: Boolean = false) {
    Economy(0, true),
    Premium(1),
    Business(2),
    First(3);

    companion object {
        private val map = values().associateBy(CabinClass::value)
        fun fromInt(type: Int) = map[type]
    }

}