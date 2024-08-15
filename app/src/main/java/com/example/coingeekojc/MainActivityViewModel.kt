package com.example.coingeekojc

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coingeekojc.ui.theme.POJO.CurrencyCategoryItem
import com.example.coingeekojc.ui.theme.POJO.CurrencyItem
import com.example.coingeekojc.ui.theme.retrofit.ApiFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _currencyList = mutableStateOf<List<CurrencyItem>>(mutableListOf())
    val currencyList: State<List<CurrencyItem>> = _currencyList

    private var originalCurrencyList: List<CurrencyItem> =
        emptyList() // Сохраняем исходные значения
    private var currentCurrency: String = "USD" // Текущая валюта

    var coinGeckoUiState by mutableStateOf<CoinGeckoUiState>(CoinGeckoUiState.Loading)
        private set

    /*
    Данный блок отвечается за инициализацию viewModel в MainActivityViewModel,
    чтобы при запуске приложения началась подгрузка данных из API в фоновом потоке
     */
    init {
        viewModelScope.launch {
            loadInitialData()
        }
    }
    /*
        Ковертация валюты в рубли и доллары
     */
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

    /*
        Инициализация данных при запуске приложения
     */

    private suspend fun loadInitialData() {
        //CoinGeckoUiState.Loading
        delay(5000L)
        coinGeckoUiState = try {
            val listCurrencyItem = ApiFactory.apiService.getCoinList()
            _currencyList.value = listCurrencyItem
            originalCurrencyList = listCurrencyItem
            CoinGeckoUiState.ListCurrencyItem(listCurrencyItem = listCurrencyItem)
        } catch (e: Exception) {
            CoinGeckoUiState.Error
        }
    }

    fun retry() {
        coinGeckoUiState = CoinGeckoUiState.Loading
        viewModelScope.launch {
            loadInitialData()
        }
    }
}