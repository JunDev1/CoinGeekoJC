package com.example.coingeekojc.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coingeekojc.MainActivityViewModel
import com.example.coingeekojc.R
import com.example.coingeekojc.ui.theme.POJO.CurrencyItem
import androidx.compose.material3.InputChip as InputChip1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCryptoScreen(currencyList: List<CurrencyItem>, viewModel: MainActivityViewModel) {
    var selectedCurrency by remember { mutableStateOf("USD") }
    var currencySymbol by remember { mutableStateOf("$") }
    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 4.dp
            ) {
                Column {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(1f),
                        title = {
                            Text(text = stringResource(R.string.title_list_crypto))
                        }
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val color = when (selectedCurrency) {
                            "USD" -> InputChipDefaults.inputChipColors(
                                selectedContainerColor = Color(
                                    0xFFFFA500
                                )
                            )

                            "RUB" -> InputChipDefaults.inputChipColors(
                                selectedContainerColor = Color(
                                    0xFFFFA500
                                )
                            )

                            else -> {
                                InputChipDefaults.inputChipColors(
                                    disabledContainerColor = Color(
                                        0xFFE0E0E0
                                    )
                                )
                            }
                        }
                        InputChip1(
                            modifier = Modifier.padding(start = 16.dp),
                            selected = selectedCurrency == "USD",
                            onClick = {
                                selectedCurrency = "USD"
                                currencySymbol = "$"
                                viewModel.convertCurrency(selectedCurrency)
                            },
                            label = { Text(text = stringResource(R.string.usd)) },
                            colors = color

                        )
                        InputChip1(
                            selected = selectedCurrency == "RUB",
                            onClick = {
                                selectedCurrency = "RUB"
                                currencySymbol = "₽"
                                viewModel.convertCurrency(selectedCurrency)
                            },
                            label = { Text(text = stringResource(R.string.rub)) },
                            colors = color
                        )
                    }
                }
            }
        }

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.navigationBarsPadding(),
            contentPadding = paddingValues,
        ) {
            items(currencyList) { currencyItem ->
                CardInfo(currencyItem = currencyItem, currencySymbol)
            }
        }
    }
}

@Composable
fun CurrencyScreen(viewModel: MainActivityViewModel) {
    val currencyList by viewModel.currencyList     // Подписываемся на изменения состояния
    Log.d("CurrencyScreen", currencyList.toString())
    ListCryptoScreen(currencyList = currencyList, viewModel)
}