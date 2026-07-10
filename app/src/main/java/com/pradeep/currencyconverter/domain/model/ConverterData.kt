package com.pradeep.currencyconverter.domain.model

data class ConverterData(
    val date: String,
    val base: String,
    val quote: String,
    val rate: Double,
    val total: Double
)
