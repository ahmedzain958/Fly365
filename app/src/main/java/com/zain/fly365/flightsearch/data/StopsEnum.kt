package com.zain.fly365.flightsearch.data

enum class StopsEnum(val index: Int) {

    NO_STOPS(0),
    ONE_STOP(1),
    TWO_STOPS(2),
    THREE_STOPS(3);

    companion object {
        private val map = values().associateBy(StopsEnum::index)
        fun fromInt(type: Int) = map[type]
    }

}