package com.pradeep.currencyconverter.domain.usecase

import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.domain.model.CurrencyRate
import com.pradeep.currencyconverter.domain.repository.CurrencyRatesRepository
import javax.inject.Inject

class GetCurrencyRatesUseCase @Inject constructor(
    private val currencyRatesRepository: CurrencyRatesRepository
) {

    suspend operator fun invoke(base: String): ApiResult<List<CurrencyRate>> =
        currencyRatesRepository.getRates(base)
}