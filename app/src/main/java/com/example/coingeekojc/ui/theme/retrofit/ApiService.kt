package com.example.coingeekojc.ui.theme.retrofit

import com.example.coingeekojc.ui.theme.POJO.Ping
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("ping")
    suspend fun getPingEndpoint(
        @Query(QUERY_PARAM_KEY) apiKey : String = "CG-89sBpagYQecJFBMA4a7dmvme"
    ) : Ping

    companion object {
        private const val QUERY_PARAM_KEY = "api_key"
    }
}