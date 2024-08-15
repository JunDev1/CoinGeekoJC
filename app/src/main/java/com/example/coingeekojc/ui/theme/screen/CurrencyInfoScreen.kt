package com.example.coingeekojc.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.coingeekojc.CoinGeckoUiState
import com.example.coingeekojc.CurrencyInfoScreenViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CurrencyInfoScreen(navController: NavController, coinId : String) {
    val viewModel: CurrencyInfoScreenViewModel = viewModel()
    val currencyCategoryItem = viewModel.currencyCategory
    LaunchedEffect(Unit) {
        viewModel.loadInitialDataCategories(coinId = coinId) // передайте нужный coinId
    }
    when (viewModel.coinGeckoUiState) {
        is CoinGeckoUiState.CategoryCurrencyItem -> {
            CurrencyInfo(currencyCategoryItem = currencyCategoryItem.value, navController = navController)
//            LazyColumn {
//                item {
//                    CurrencyInfo(currencyCategoryItem = currencyCategoryItem.value, navController = navController)
//
//                }
//            }
        }
        is CoinGeckoUiState.Error -> ErrorCurrencyScreen(navController)
        is CoinGeckoUiState.ListCurrencyItem -> {  }
        is CoinGeckoUiState.Loading -> LoadingCurrencyScreen()
    }
}