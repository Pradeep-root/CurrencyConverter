package com.pradeep.currencyconverter.data.repository

import com.pradeep.currencyconverter.core.NetworkErrorMapper
import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.core.dispatcher.DispatcherProvider
import com.pradeep.currencyconverter.data.api.FrankFurterApiService
import com.pradeep.currencyconverter.data.mapper.toConverterData
import com.pradeep.currencyconverter.data.mapper.toDomain
import com.pradeep.currencyconverter.domain.model.ConverterData
import com.pradeep.currencyconverter.domain.model.CurrencyRate
import com.pradeep.currencyconverter.domain.repository.CurrencyRatesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRatesRepositoryImpl @Inject constructor(
    private val apiService: FrankFurterApiService,
    private val dispatcherProvider: DispatcherProvider,
    private val networkErrorMapper: NetworkErrorMapper
) : CurrencyRatesRepository {

    override suspend fun getRates(base: String): ApiResult<List<CurrencyRate>> =
        withContext(dispatcherProvider.io) {
            try {
                val response = apiService.getRates(base)
                ApiResult.Success(response.map { it.toDomain() })
            } catch (e: Exception) {
                ApiResult.Error(networkErrorMapper.map(e))
            }
        }

    override suspend fun getRate(
        base: String,
        quote: String
    ): ApiResult<ConverterData> = withContext(dispatcherProvider.io) {
        try {
            val response = apiService.getRate(base, quote)
            ApiResult.Success(response.toConverterData())
        } catch (e: Exception) {
            ApiResult.Error(networkErrorMapper.map(e))
        }
    }

    override suspend fun getHistoricalRates(
        from: String,
        base: String,
        quote: String
    ): ApiResult<List<CurrencyRate>> = withContext(dispatcherProvider.io) {
        try {
            val response = apiService.getHistoricalData(from, base, quote)
            ApiResult.Success(response.map { it.toDomain() })
        } catch (e: Exception) {
            ApiResult.Error(networkErrorMapper.map(e))
        }
    }
}