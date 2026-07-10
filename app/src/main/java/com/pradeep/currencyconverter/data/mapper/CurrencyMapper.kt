package com.pradeep.currencyconverter.data.mapper

import com.pradeep.currencyconverter.data.dto.CurrencyRateDto
import com.pradeep.currencyconverter.domain.model.ConverterData
import com.pradeep.currencyconverter.domain.model.CurrencyRate

fun CurrencyRateDto.toDomain(): CurrencyRate {
    return CurrencyRate(
        date = date,
        base = base,
        quote = quote,
        rate = rate
    )
}

fun CurrencyRateDto.toConverterData(): ConverterData {
    return ConverterData(
        date = date,
        base = base,
        quote = quote,
        rate = rate,
        total = 0.0
    )
}