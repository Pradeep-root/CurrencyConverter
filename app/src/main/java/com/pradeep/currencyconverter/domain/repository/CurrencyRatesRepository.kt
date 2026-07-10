package com.pradeep.currencyconverter.domain.repository

import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.domain.model.ConverterData
import com.pradeep.currencyconverter.domain.model.CurrencyRate

interface CurrencyRatesRepository {

   suspend fun getRates(base: String): ApiResult<List<CurrencyRate>>

   suspend fun getRate(base: String, quote: String): ApiResult<ConverterData>

}