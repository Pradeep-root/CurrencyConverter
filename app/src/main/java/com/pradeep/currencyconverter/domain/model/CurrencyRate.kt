package com.pradeep.currencyconverter.domain.model

data class CurrencyRate(
    val date: String,
    val base: String,
    val quote: String,
    val rate: Double,
    val flagUrl: String
)
