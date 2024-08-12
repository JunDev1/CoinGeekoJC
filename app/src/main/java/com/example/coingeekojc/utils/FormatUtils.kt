package com.example.coingeekojc.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

fun formatPrice(price: Double): String {
    val decimalFormat = DecimalFormat("##,##0.00")
    return decimalFormat.format(price)
}

fun formatPercentageChange(change: Double): String {
    val decimalFormat = DecimalFormat("#.#")
    val formattedChanged = decimalFormat.format(change)
    return if (change >= 0) "+$formattedChanged %" else "$formattedChanged %"
}

@Composable
fun PercentageChangeText(change: Double) {
    val color = if (change >= 0) Color.Green else Color.Red
    Text(
        text = formatPercentageChange(change),
        color = color,
        fontSize = 12.sp
    )
}