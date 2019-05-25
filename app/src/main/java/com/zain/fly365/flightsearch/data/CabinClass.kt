package com.zain.fly365.flightsearch.data

enum class CabinClass(val value: Int, var isSelected: Boolean = false) {
    Economy(0),
    Premium(1),
    Business(2),
    First(3)
}