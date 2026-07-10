package com.pradeep.currencyconverter.domain.usecase

import com.pradeep.currencyconverter.domain.model.ConverterData

import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.domain.repository.CurrencyRatesRepository
import javax.inject.Inject

class GetExchangeRateUseCase @Inject constructor(
    private val repository: CurrencyRatesRepository
) {

    suspend operator fun invoke(base: String, quote: String): ApiResult<ConverterData> =
        repository.getRate(base, quote)

}