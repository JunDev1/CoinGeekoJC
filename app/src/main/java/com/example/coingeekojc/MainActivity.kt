package com.example.coingeekojc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.coingeekojc.ui.theme.CoinGeekoJCTheme
import com.example.coingeekojc.ui.theme.screen.CurrencyScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            CoinGeekoJCTheme {
                CurrencyScreen(viewModel)
            }
        }
    }
}

