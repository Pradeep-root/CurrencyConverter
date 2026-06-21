package com.pradeep.currencyconverter.core.common

sealed class AppException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {

    class Network(cause: Throwable? = null, message: String? = null) : AppException(message, cause)
    class Server(val code: Int, message: String? = null) : AppException(message)
    class Unknown(cause: Throwable? = null) : AppException(cause?.message, cause)
}

fun AppException.toUserMessage(): String = when (this) {
    is AppException.Network -> message?.takeIf { it.isNotBlank() } ?: "No network connection."
    is AppException.Server -> "Server returns with error $code"
    is AppException.Unknown -> message?.takeIf { it.isNotBlank() } ?: "Something went wrong."
}