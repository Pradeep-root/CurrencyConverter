package com.pradeep.currencyconverter.data.api

import com.pradeep.currencyconverter.data.dto.CurrencyRateDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FrankFurterApiService {


    @GET("/v2/rates")
    suspend fun getRates(@Query("base") base: String): List<CurrencyRateDto>

    @GET("/v2/rate/{base}/{quote}")
    suspend fun getRate(@Path("base") base: String, @Path("quote") quote: String): CurrencyRateDto
}