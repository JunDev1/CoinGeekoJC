package com.example.coingeekojc.ui.theme.navigation

import com.example.coingeekojc.ui.theme.POJO.CurrencyCategoryItem

sealed class Screen(
    val route: String,
) {
    object ErrorScreen : Screen(ROUTE_ERROR)
    object LoadingScreen : Screen(ROUTE_LOADING)

    object CurrencyInfoScreen : Screen(ROUTE_CURRENCY_INFO) {
        private const val ROUTE_COIN_ID = "CurrencyInfoScreen"
        fun getRouteWithArgs(currencyCategoryItem: CurrencyCategoryItem): String {
            return "$ROUTE_COIN_ID/${currencyCategoryItem.id}"
        }
    }

    object ListCryptoScreenScreen : Screen(ROUTE_LIST_CRYPTO)

    companion object {
        const val ROUTE_ERROR = "ErrorScreen"
        const val ROUTE_LOADING = "LoadingScreen"
        const val ROUTE_CURRENCY_INFO = "CurrencyInfoScreen"
        const val ROUTE_LIST_CRYPTO = "ListCryptoScreen"
    }
}