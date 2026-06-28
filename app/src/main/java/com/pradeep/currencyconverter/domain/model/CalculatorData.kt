package com.pradeep.currencyconverter.domain.model

data class CalculatorData(
    val baseInputFieldData: InputFieldData,
    val quoteInputFieldData: InputFieldData
)

data class InputFieldData(
    val flagUrl: String,
    val symbol: String,
    val rate: String,
    val total: String
)
