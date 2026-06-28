package com.pradeep.currencyconverter.data.mapper

import com.pradeep.currencyconverter.data.dto.CurrencyRateDto
import com.pradeep.currencyconverter.data.local.CurrencyFlags
import com.pradeep.currencyconverter.domain.model.CurrencyRate

fun CurrencyRateDto.toDomain(): CurrencyRate {
    return CurrencyRate(
        date = date,
        base = base,
        quote = quote,
        rate = rate
    )
}