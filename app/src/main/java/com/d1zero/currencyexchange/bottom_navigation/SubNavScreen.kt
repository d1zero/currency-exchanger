package com.d1zero.currencyexchange.bottom_navigation

sealed class SubNavScreen (
    val route: String,
    val title: String
) {
    object Trade : SubNavScreen(
        route = "trade",
        title = "Обмен"
    )
}