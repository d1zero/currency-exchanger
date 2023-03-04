package com.d1zero.currencyexchange.bottom_navigation

import com.d1zero.currencyexchange.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String) {
    object Screen1 : BottomItem("Список валют", R.drawable.currency_list, "currency_list")
    object Screen2 : BottomItem("История операций", R.drawable.history, "history")
    object Screen3 : BottomItem("График", R.drawable.graphic, "graphic")
}
