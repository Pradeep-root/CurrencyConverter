package com.pradeep.currencyconverter.domain.usecase

import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.core.common.map
import com.pradeep.currencyconverter.domain.model.ConverterData
import com.pradeep.currencyconverter.domain.repository.CurrencyRatesRepository
import javax.inject.Inject

class ConvertUseCase @Inject constructor(
    private val repository: CurrencyRatesRepository
) {

    suspend operator fun invoke(amount: Double, base: String, quote: String): ApiResult<ConverterData> =
        repository.getRate(base, quote).map { data ->
            data.copy(total = amount * data.rate)
        }

}