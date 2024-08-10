package com.example.coingeekojc

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.rememberCoroutineScope
import com.example.coingeekojc.ui.theme.CoinGeekoJCTheme
import com.example.coingeekojc.ui.theme.retrofit.ApiFactory
import com.example.coingeekojc.ui.theme.retrofit.ApiService
import com.example.coingeekojc.ui.theme.screen.CurrencyInfo
import com.example.coingeekojc.ui.theme.screen.ErrorScreen
import com.example.coingeekojc.ui.theme.screen.ListCryptoScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinGeekoJCTheme {
                val scope = rememberCoroutineScope()
                scope.launch {
                    val endpoint = ApiFactory.apiService.getPingEndpoint()
                    Log.d("MainActivity", endpoint.toString())
                }
//                ErrorScreen {
//
//                }
                //ListCryptoScreen()
                //CurrencyInfo()
            }
        }
    }
}