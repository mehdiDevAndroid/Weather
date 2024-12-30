package com.devem.weather.domain.retrofit

import android.content.Context
import com.devem.weather.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class NoConnectivityException  @Inject constructor(
    @ApplicationContext private val mContext: Context
): IOException() {
    override val message: String
        get() = mContext.getString(R.string.message_internet_problem)
}