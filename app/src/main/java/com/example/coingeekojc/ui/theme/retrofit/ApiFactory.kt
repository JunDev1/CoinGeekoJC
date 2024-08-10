package com.example.coingeekojc.ui.theme.retrofit

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://api.coingecko.com/api/v3/"
    private val contentType = "application/json".toMediaType()
    private val networkJson = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(networkJson.asConverterFactory(contentType))
        .build()
    val apiService = retrofit.create(ApiService::class.java)
}