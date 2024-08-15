package com.example.coingeekojc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coingeekojc.ui.theme.CoinGeekoJCTheme
import com.example.coingeekojc.ui.theme.POJO.CurrencyCategoryItem
import com.example.coingeekojc.ui.theme.screen.CurrencyInfoScreen
import com.example.coingeekojc.ui.theme.screen.ListCryptoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            CoinGeekoJCTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = when (viewModel.coinGeckoUiState) {
                        is CoinGeckoUiState.ListCurrencyItem -> "ListCryptoScreen"
                        is CoinGeckoUiState.Loading -> "LoadingScreen"
                        is CoinGeckoUiState.CategoryCurrencyItem -> "CurrencyInfoScreen"
                        is CoinGeckoUiState.Error -> "ErrorScreen"
                    }
                ) {
                    composable("LoadingScreen") {

                    }
                    composable("CurrencyInfoScreen/{coinId}") {
                        val coinId = it.arguments?.getString("coinId") ?: "bitcoin"
                        CurrencyInfoScreen(navController, coinId)
                    }
                    composable("ListCryptoScreen") {
                        ListCryptoScreen(navController)
                    }
                    composable("ErrorScreen") {

                    }
                }
            }
        }
    }
}

