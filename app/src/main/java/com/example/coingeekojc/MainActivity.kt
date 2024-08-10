package com.example.coingeekojc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.coingeekojc.ui.theme.CoinGeekoJCTheme
import com.example.coingeekojc.ui.theme.screen.CurrencyInfo
import com.example.coingeekojc.ui.theme.screen.ErrorScreen
import com.example.coingeekojc.ui.theme.screen.ListCryptoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinGeekoJCTheme {
//                ErrorScreen {
//
//                }
                //ListCryptoScreen()
                CurrencyInfo()
            }
        }
    }
}