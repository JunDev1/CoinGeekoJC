package com.example.coingeekojc

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coingeekojc.ui.theme.POJO.CurrencyItem
import com.example.coingeekojc.ui.theme.retrofit.ApiFactory
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val _currencyList = mutableStateOf<List<CurrencyItem>>(mutableListOf())
    val currencyList: State<List<CurrencyItem>> = _currencyList
    private var originalCurrencyList: List<CurrencyItem> = emptyList() // Сохраняем исходные значения
    private var currentCurrency: String = "USD" // Текущая валюта
    init {
        viewModelScope.launch {
            loadInitialData()
        }
    }
    fun convertCurrency(toCurrency: String) {
        if (toCurrency == currentCurrency) return // Если валюта уже выбрана, ничего не делаем
        if (originalCurrencyList.isEmpty()) {
            originalCurrencyList = _currencyList.value // Сохраняем исходные значения
        }
        _currencyList.value = if (toCurrency == "RUB") {
            originalCurrencyList.map { currencyItem ->
                currencyItem.copy(currentPrice = currencyItem.currentPrice?.times(74)) // Пример конвертации USD в RUB
            }
        } else {
            originalCurrencyList.map { currencyItem ->
                currencyItem.copy(currentPrice = currencyItem.currentPrice) // Возвращаем исходные значения для USD
            }
        }

        currentCurrency = toCurrency // Обновляем текущую валюту
    }

    private suspend fun loadInitialData() {
        try {
            val result = ApiFactory.apiService.getCoinList()
            if (result.isEmpty()) {
                Log.d("MainActivityViewModel", "Список пуст")
            } else {
                _currencyList.value = result
                Log.d("MainActivityViewModel", "Список обновлен: ${_currencyList.value.size} элементов")
            }
        } catch (e: Exception) {
            Log.e("MainActivityViewModel", e.message.toString())
        }
    }


}