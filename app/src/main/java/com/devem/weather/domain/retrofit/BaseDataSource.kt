package com.devem.weather.domain.retrofit

import android.content.Context
import com.devem.weather.domain.enums.ErrorTypes
import com.devem.weather.utils.Extensions.logInfo
import com.devem.weather.utils.NetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject

class BaseDataSource @Inject constructor(
    @ApplicationContext private val mContext: Context
) {

    @Inject
    lateinit var noConnectivityException: NoConnectivityException

    suspend fun <T : Any> getResult(
        call: suspend () -> Response<T>
    ): State<T> {

        return try {
            if(!NetworkUtils.isConnected(mContext)) {
                // Throwing our custom exception 'NoConnectivityException'
                throw noConnectivityException
            }else {
                logInfo("********************************* Start WS CAL *********************************")
                val response = call()

                logInfo("** LOG WS **")
                logInfo("Message ==> ${response.message()}")
                logInfo("ErrorBody ==> ${response.errorBody()}")
                for(header in response.headers().toMultimap()){
                    logInfo("Header[${header.key}]-> ${header.value}")
                }
                logInfo("Raw  ==> ${response.raw()}")
                if(response.isSuccessful)
                    logInfo("Data  ==> ${response.body()}")

                if (response.isSuccessful)
                    // if the response is successful
                    State.Success(data = response.body())

                else {
                    // case when we have an error
                    getError(response = response)
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            getError(exception = exception)
        }
        finally {
            logInfo("********************************* END WS CAL *********************************")
        }
    }

    private fun <T> getError(exception: Exception?): State<T> {
        val errorType = when(exception){
            is NoConnectivityException -> ErrorTypes.UNAVAILABLE_NETWORK
            else -> ErrorTypes.TECHNICAL
        }
        return State.Error(
            message = mContext.getString(errorType.message),
        )
    }


    private fun <T> getError(
        response: Response<T>?
    ): State.Error<T> {
        val httpCode = response?.code()

        val errorType = ErrorTypes.fromHttpCode(httpCode) ?: ErrorTypes.TECHNICAL

        return State.Error(
            message = mContext.getString(errorType.message)
        )
    }

}