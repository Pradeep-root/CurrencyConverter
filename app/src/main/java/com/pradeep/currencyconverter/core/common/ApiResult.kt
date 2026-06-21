package com.pradeep.currencyconverter.core.common

sealed class ApiResult<out T> {

    data class Success<T>(val data: T): ApiResult<T>()
    data class Error(val exception: AppException): ApiResult<Nothing>()
}