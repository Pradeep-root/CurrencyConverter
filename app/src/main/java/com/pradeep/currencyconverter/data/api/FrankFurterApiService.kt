package com.pradeep.currencyconverter.data.api

import com.pradeep.currencyconverter.data.dto.CurrencyRateDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FrankFurterApiService {


    @GET("/v2/rates")
    suspend fun getRates(@Query("base") base: String ): List<CurrencyRateDto>
}