package com.pradeep.currencyconverter.core

import com.pradeep.currencyconverter.core.common.AppException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkErrorMapper @Inject constructor() {

    fun map(throwable: Throwable): AppException = when (throwable) {
        is HttpException         -> AppException.Server(code = throwable.code(), message = throwable.message())
        is UnknownHostException  -> AppException.Network(cause = throwable, message = "No internet connection.")
        is SocketTimeoutException -> AppException.Network(cause = throwable, message = "Connection timed out.")
        is IOException           -> AppException.Network(cause = throwable)
        is AppException          -> throwable
        else                     -> AppException.Unknown(cause = throwable)
    }
}
