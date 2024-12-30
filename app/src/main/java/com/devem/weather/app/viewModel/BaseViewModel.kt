package com.devem.weather.app.viewModel


import androidx.lifecycle.ViewModel
import com.devem.weather.utils.Extensions.logError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel(){

    fun handleCancellableJobFailure(
        logMessage: String?,
        exception: Exception?,
        onFailure: () -> Unit,
        onJobCancellationExceptionCallback: (() -> Unit)? = null
    ){
        logError("***************** ERROR ***************** \n" +
                "$logMessage (Error) : \n" +
                "${exception?.message} \n" +
                "***************** END ERROR *****************"
        )
        when(exception){
            !is CancellationException -> {
                onFailure.invoke()
            }
            else -> {
                onJobCancellationExceptionCallback?.invoke()
            }

        }
    }
}