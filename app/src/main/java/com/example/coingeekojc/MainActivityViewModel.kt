package com.example.coingeekojc

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coingeekojc.ui.theme.POJO.CurrencyItem
import com.example.coingeekojc.ui.theme.retrofit.ApiFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _currencyList = mutableStateOf<List<CurrencyItem>>(emptyList())
    val currencyList: State<List<CurrencyItem>> = _currencyList

    private var originalCurrencyList: List<CurrencyItem> = emptyList()
    private var currentCurrency: String = "USD"

    var coinGeckoUiState by mutableStateOf<CoinGeckoUiState>(CoinGeckoUiState.Loading)
        private set
    init {
        loadInitialData()
    }
    private fun setLoadingState() {
        coinGeckoUiState = CoinGeckoUiState.Loading
    }
    private fun setErrorState() {
        coinGeckoUiState = CoinGeckoUiState.Error
    }
    private fun setListCurrencyState(listCurrencyItem: List<CurrencyItem>) {
        _currencyList.value = listCurrencyItem
        originalCurrencyList = listCurrencyItem
        coinGeckoUiState = CoinGeckoUiState.ListCurrencyItem(listCurrencyItem)
    }
    private suspend fun fetchCurrencyData(): List<CurrencyItem> {
        delay(2000L) // Имитируем загрузку данных
        return ApiFactory.apiService.getCoinList()
    }
    private fun handleConversion(toCurrency: String) {
        _currencyList.value = if (toCurrency == "RUB") {
            originalCurrencyList.map { currencyItem ->
                currencyItem.copy(currentPrice = currencyItem.currentPrice?.times(74)) // Пример конвертации USD в RUB
            }
        } else {
            originalCurrencyList // Возвращаем исходные значения для USD
        }
        currentCurrency = toCurrency
    }
    fun loadInitialData() {
        setLoadingState()
        viewModelScope.launch {
            try {
                val listCurrencyItem = fetchCurrencyData()
                setListCurrencyState(listCurrencyItem)
            } catch (e: Exception) {
                setErrorState()
            }
        }
    }
    fun convertCurrency(toCurrency: String) {
        if (toCurrency == currentCurrency) return
        if (originalCurrencyList.isEmpty()) {
            originalCurrencyList = _currencyList.value
        }
        handleConversion(toCurrency)
    }
    fun retry() {
        loadInitialData()
    }
}
