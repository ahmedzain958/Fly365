package com.zain.fly365.flightsearch.data

enum class AirlinesEnum(val index: Int, var value: String) {

    TURKISH_AIRLINES(0, "Turkish Airlines"),
    BRITISH_AIRWAYS(1,"British Airways"),
    EMIRATES_AIRLINES(2,"Emirates"),
    KLM_AIRLINES(3,"KLM"),
    QANTAS_AIRLINES(3,"QANTAS"),
    ROYAL_JORDANIAN_AIRLINES(3,"ROYAL JORDANIAN");

    companion object {
        private val map = values().associateBy(AirlinesEnum::index)
        fun fromInt(type: Int) = map[type]
    }

}