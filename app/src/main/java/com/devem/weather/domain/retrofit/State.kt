package com.devem.weather.domain.retrofit


sealed class State<T>(val data: T?, val message: String?) {
    class Success<T>(data: T?, message: String? = null) : State<T>(data, null)
    class Error<T>(data: T? = null, message: String?) : State<T>(null, message)
    class Loading<T> : State<T>(null, null)

    fun <U : Any> mapTo(
        dataConverter: (T?) -> U?,
        mMessage: String? = null,
    ): State<U> {
        return when (this) {
            is Success -> {
                Success(
                    data = dataConverter(data)
                )
            }

            is Error -> {
                Error(
                    data = null,
                    message = mMessage?: message
                )
            }

            is Loading -> {
                Loading()
            }
        }
    }
}