package com.pradeep.currencyconverter.data.dto

import com.google.gson.annotations.SerializedName

data class CurrencyRateDto(
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("quote") val quote: String,
    @SerializedName("rate") val rate: Double
)