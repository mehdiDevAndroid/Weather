package com.devem.weather.domain.enums


enum class TimeStepTypes(val id: String?){
    CURRENT("current"),
    HOUR("1h"),
    DAY("1d");

    companion object {
        fun fromId(id: String?): TimeStepTypes? {
            return entries.find { it.id == id }
        }
    }
}

