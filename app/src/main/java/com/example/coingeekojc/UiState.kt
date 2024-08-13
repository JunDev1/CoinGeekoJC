package com.example.coingeekojc

import com.example.coingeekojc.ui.theme.POJO.CurrencyItem


/*
    Создание состояний приложения
 */
sealed interface UiState {
    data class Success(val listCurrencyItem : List<CurrencyItem>) : UiState
    object Loading : UiState
    object Error : UiState
}