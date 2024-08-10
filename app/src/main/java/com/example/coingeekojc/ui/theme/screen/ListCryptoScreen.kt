package com.example.coingeekojc.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableChipColors
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
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coingeekojc.R
import androidx.compose.material3.InputChip as InputChip1

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ListCryptoScreen() {
    var selectedCurrency by remember { mutableStateOf("USD") }
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
                            "USD" -> InputChipDefaults.inputChipColors(selectedContainerColor = Color(0xFFFFA500))
                            "RUB" -> InputChipDefaults.inputChipColors(selectedContainerColor = Color(0xFFFFA500))
                            else -> {
                                InputChipDefaults.inputChipColors(disabledContainerColor = Color(0xFFE0E0E0))
                            }
                        }
                        InputChip1(
                            modifier = Modifier.padding(start = 16.dp),
                            selected = selectedCurrency == "USD",
                            onClick = { selectedCurrency = "USD" },
                            label = { Text(text = stringResource(R.string.usd)) },
                            colors = color

                        )
                        InputChip1(
                            selected = selectedCurrency == "RUB",
                            onClick = { selectedCurrency = "RUB" },
                            label = { Text(text = stringResource(R.string.rub)) },
                            colors = color
                        )
                    }
                }
            }
        }

    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(20) {
                CardInfo()
            }
        }

    }
}