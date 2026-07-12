package com.pradeep.currencyconverter.domain.usecase

import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.domain.model.CurrencyRate
import com.pradeep.currencyconverter.domain.repository.CurrencyRatesRepository
import javax.inject.Inject

class GetHistoricalRatesUseCase @Inject constructor(
    private val repository: CurrencyRatesRepository
) {

    suspend operator fun invoke(from: String, base: String, quote: String): ApiResult<List<CurrencyRate>> =
        repository.getHistoricalRates(from, base, quote)
}