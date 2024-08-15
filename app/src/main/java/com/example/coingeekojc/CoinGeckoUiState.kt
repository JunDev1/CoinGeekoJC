package com.example.coingeekojc

import com.example.coingeekojc.ui.theme.POJO.CurrencyCategoryItem
import com.example.coingeekojc.ui.theme.POJO.CurrencyItem
import kotlinx.serialization.Serializable


/*
    Создание состояний приложения
 */
@Serializable
sealed interface CoinGeckoUiState {
    data class ListCurrencyItem(val listCurrencyItem : List<CurrencyItem>) : CoinGeckoUiState
    data class CategoryCurrencyItem(val categoryCurrencyItem : CurrencyCategoryItem) : CoinGeckoUiState
    @Serializable
    object Loading : CoinGeckoUiState
    @Serializable
    object Error : CoinGeckoUiState
}