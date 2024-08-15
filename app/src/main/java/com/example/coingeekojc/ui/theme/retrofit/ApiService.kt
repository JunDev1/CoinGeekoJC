package com.example.coingeekojc.ui.theme.retrofit

import com.example.coingeekojc.ui.theme.POJO.CurrencyCategoryItem
import com.example.coingeekojc.ui.theme.POJO.CurrencyItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("coins/markets")
    suspend fun getCoinList(
        @Query(QUERY_PARAM_KEY) apiKey: String = "CG-89sBpagYQecJFBMA4a7dmvme",
        @Query(QUERY_PARAM_CURRENCY) vsCurrency: String = "usd",
    ): List<CurrencyItem>

    @GET("coins/{id}")
    suspend fun getCurrencyItemCategory(
        @Path(QUERY_PARAM_ID) id: String,
        @Query(QUERY_PARAM_KEY) apiKey: String = "CG-89sBpagYQecJFBMA4a7dmvme",
        @Query(QUERY_PARAM_LOCALIZATION) localization : Boolean = false,
        @Query(QUERY_PARAM_TICKERS) tickers : Boolean = false,
        @Query(QUERY_PARAM_MARKET_DATA) marketData: Boolean = false,
        @Query(QUERY_PARAM_COMMUNITY_DATA) communityData : Boolean = false,
        @Query(QUERY_PARAM_DEVELOPER_DATA) developerData : Boolean = false,
        @Query(QUERY_PARAM_SPARKLINE) sparkline: Boolean = false,
    ): CurrencyCategoryItem

    companion object {
        private const val QUERY_PARAM_KEY = "api_key"
        private const val QUERY_PARAM_CURRENCY = "vs_currency"
        private const val QUERY_PARAM_ID = "id"
        private const val QUERY_PARAM_LOCALIZATION = "localization"
        private const val QUERY_PARAM_TICKERS = "tickers"
        private const val QUERY_PARAM_MARKET_DATA = "market_data"
        private const val QUERY_PARAM_COMMUNITY_DATA = "community_data"
        private const val QUERY_PARAM_DEVELOPER_DATA = "developer_data"
        private const val QUERY_PARAM_SPARKLINE = "sparkline"
    }

}