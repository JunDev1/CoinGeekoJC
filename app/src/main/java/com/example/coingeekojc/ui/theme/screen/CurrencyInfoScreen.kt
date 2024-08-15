package com.example.coingeekojc.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coingeekojc.CoinGeckoUiState
import com.example.coingeekojc.CurrencyInfoScreenViewModel
import com.example.coingeekojc.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CurrencyInfoScreen(
    navController: NavController,
    coinId: String,
) {
    val viewModel: CurrencyInfoScreenViewModel = viewModel()
    val currencyCategoryItem = viewModel.currencyCategory
    LaunchedEffect(Unit) {
        viewModel.loadInitialDataCategories(coinId = coinId)
    }
    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 4.dp
            ) {
                Column {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(1f),
                        title = {
                            Text(text = coinId.replaceFirstChar { it.uppercase() })
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.navigate("ListCryptoScreen")
                            }) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(28.dp)
                                )
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        when (viewModel.coinGeckoUiState) {
            is CoinGeckoUiState.CategoryCurrencyItem -> {
                CurrencyInfo(
                    currencyCategoryItem = currencyCategoryItem.value,
                )
            }
            is CoinGeckoUiState.Error -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.btc), // Замените на ваш ресурс изображения
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.something_wrong),
                            style = TextStyle(textIndent = TextIndent(20.sp, 25.sp)),
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                viewModel.retry(coinId)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500))
                        ) {
                            Text(text = stringResource(R.string.try_again))
                        }
                    }
                }
            }
            is CoinGeckoUiState.ListCurrencyItem -> {}
            is CoinGeckoUiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    CircularProgressIndicator(
                        color = Color(0xFFFFA500)
                    )
                }
            }
        }
    }
}