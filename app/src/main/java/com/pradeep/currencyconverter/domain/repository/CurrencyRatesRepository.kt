package com.pradeep.currencyconverter.domain.repository

import androidx.datastore.preferences.protobuf.Api
import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.domain.model.ConverterData
import com.pradeep.currencyconverter.domain.model.CurrencyRate

interface CurrencyRatesRepository {

   suspend fun getRates(base: String): ApiResult<List<CurrencyRate>>

   suspend fun getRate(base: String, quote: String): ApiResult<ConverterData>

   suspend fun getHistoricalRates(from: String, base: String, quote: String): ApiResult<List<CurrencyRate>>

}