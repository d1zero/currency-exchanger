package com.d1zero.currencyexchange.bottom_navigation

import com.d1zero.currencyexchange.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String) {
    object Screen1 : BottomItem("Screen 1", R.drawable.currency_list, "screen_1")
    object Screen2 : BottomItem("Screen 2", R.drawable.currency_list, "screen_2")
    object Screen3 : BottomItem("Screen 3", R.drawable.currency_list, "screen_3")
}
