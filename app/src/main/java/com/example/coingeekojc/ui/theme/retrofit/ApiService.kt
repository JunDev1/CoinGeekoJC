package com.example.coingeekojc.ui.theme.retrofit

import com.example.coingeekojc.ui.theme.POJO.CurrencyItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("coins/markets")
    suspend fun getCoinList(
        @Query(QUERY_PARAM_KEY) apiKey: String = "CG-89sBpagYQecJFBMA4a7dmvme",
        @Query(QUERY_PARAM_CURRENCY) vsCurrency: String = "usd",
    ): List<CurrencyItem>

    companion object {
        private const val QUERY_PARAM_KEY = "api_key"
        private const val QUERY_PARAM_CURRENCY = "vs_currency"
    }

}