package com.pradeep.currencyconverter.core.common

/**
 * Transforms the data inside [ApiResult.Success] using [transform].
 * Passes [ApiResult.Error] through unchanged.
 */
inline fun <T, R> ApiResult<T>.map(transform: (T) -> R): ApiResult<R> = when (this) {
    is ApiResult.Success -> ApiResult.Success(transform(data))
    is ApiResult.Error -> this
}

/**
 * Runs [action] if this is [ApiResult.Success]. Returns the original result for chaining.
 */
inline fun <T> ApiResult<T>.onSuccess(action: (T) -> Unit): ApiResult<T> {
    if (this is ApiResult.Success) action(data)
    return this
}

/**
 * Runs [action] if this is [ApiResult.Error]. Returns the original result for chaining.
 */
inline fun <T> ApiResult<T>.onError(action: (AppException) -> Unit): ApiResult<T> {
    if (this is ApiResult.Error) action(exception)
    return this
}

/**
 * Returns data if [ApiResult.Success], null otherwise.
 */
fun <T> ApiResult<T>.getOrNull(): T? = (this as? ApiResult.Success)?.data

/**
 * Returns data if [ApiResult.Success], otherwise returns [default].
 */
fun <T> ApiResult<T>.getOrDefault(default: T): T = (this as? ApiResult.Success)?.data ?: default
