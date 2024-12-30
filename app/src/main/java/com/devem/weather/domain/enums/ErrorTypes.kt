package com.devem.weather.domain.enums

import com.devem.weather.R


enum class ErrorTypes(
    val httpCode: Int?,
    val message: Int = R.string.message_server_problem
    ){
    UNAVAILABLE_NETWORK(null ,  R.string.message_internet_problem),
    TECHNICAL(404 ,  R.string.message_server_problem);

    companion object {

        fun fromHttpCode(code:Int?): ErrorTypes? {
            return if(code == null)
                TECHNICAL
            else
                entries.find { it.httpCode == code }
        }
    }
}

