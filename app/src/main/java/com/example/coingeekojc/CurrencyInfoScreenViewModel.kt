package com.example.coingeekojc

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coingeekojc.ui.theme.POJO.CurrencyCategoryItem
import com.example.coingeekojc.ui.theme.retrofit.ApiFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CurrencyInfoScreenViewModel() : ViewModel() {

    private val _currencyCategory = mutableStateOf(CurrencyCategoryItem())
    val currencyCategory: State<CurrencyCategoryItem> = _currencyCategory

    var coinGeckoUiState by mutableStateOf<CoinGeckoUiState>(CoinGeckoUiState.Loading)
        private set

    suspend fun loadInitialDataCategories(coinId: String) {
        delay(5000L)
        Log.d("loadInitialDataCategories", "Fetching data for coinId: $coinId")
        coinGeckoUiState = try {
            val currencyCategoryData = ApiFactory.apiService.getCurrencyItemCategory(id = coinId)
            Log.d("loadInitialDataCategories", "Data received: $currencyCategoryData")
            _currencyCategory.value = currencyCategoryData
            CoinGeckoUiState.CategoryCurrencyItem(categoryCurrencyItem = currencyCategoryData)
        } catch (e: Exception) {
            Log.e("loadInitialDataCategories", "Error fetching data: ${e.message}")
            CoinGeckoUiState.Error
        }
    }

    fun retry(coinId: String) {
        viewModelScope.launch {
            loadInitialDataCategories(coinId)
        }
    }
}