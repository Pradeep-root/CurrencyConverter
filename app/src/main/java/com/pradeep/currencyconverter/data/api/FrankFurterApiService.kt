package com.pradeep.currencyconverter.data.api

import com.pradeep.currencyconverter.data.dto.CurrencyRateDto
import retrofit2.http.GET

interface FrankFurterApiService {


    @GET("/v2/rates")
    suspend fun getRates(): List<CurrencyRateDto>
}