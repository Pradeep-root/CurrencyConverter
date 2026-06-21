package com.pradeep.currencyconverter.domain.repository

import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.domain.model.CurrencyRate

interface CurrencyRatesRepository {

   suspend fun getRates(): ApiResult<List<CurrencyRate>>
}