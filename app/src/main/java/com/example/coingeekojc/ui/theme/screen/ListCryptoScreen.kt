package com.example.coingeekojc.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.coingeekojc.CoinGeckoUiState
import com.example.coingeekojc.MainActivityViewModel
import com.example.coingeekojc.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCryptoScreen(
    navController: NavController,
) {
    var selectedCurrency by remember { mutableStateOf("USD") }
    var currencySymbol by remember { mutableStateOf("$") }
    val viewModel: MainActivityViewModel = viewModel()
    val currencyItems = viewModel.currencyList
    val coinGeckoUiState = viewModel.coinGeckoUiState

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
                            .fillMaxWidth()
                            .padding(start = 16.dp, bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ChipGroup(
                            selectedCurrency = selectedCurrency,
                            onCurrencySelected = { currency ->
                                selectedCurrency = currency
                                currencySymbol = if (currency == "USD") "$" else "₽"
                                viewModel.convertCurrency(selectedCurrency)
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        when (coinGeckoUiState) {
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
                                viewModel.retry()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500))
                        ) {
                            Text(text = stringResource(R.string.try_again))
                        }
                    }
                }
            }
            is CoinGeckoUiState.ListCurrencyItem -> {
                LazyColumn(
                    modifier = Modifier.navigationBarsPadding(),
                    contentPadding = paddingValues,
                ) {
                    items(
                        currencyItems.value,
                    ) { currencyItem ->
                        CardInfo(currencyItem, currencySymbol, navController = navController)
                    }
                }
            }
            is CoinGeckoUiState.CategoryCurrencyItem -> {}
        }
    }
}

@Composable
fun CustomChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val backgroundColor = if (isSelected) Color(0xFFFCEBCD) else Color(0xFFE0E0E0)
    val textColor = if (isSelected) Color(0xFFFFA603) else Color.Gray

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
            .height(35.dp)
            .width(100.dp) //
    ) {
        Text(
            text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ChipGroup(
    selectedCurrency: String,
    onCurrencySelected: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomChip(
            text = "USD",
            isSelected = selectedCurrency == "USD",
            onClick = {
                onCurrencySelected("USD")
            }
        )
        CustomChip(
            text = "RUB",
            isSelected = selectedCurrency == "RUB",
            onClick = {
                onCurrencySelected("RUB")
            }
        )
    }
}