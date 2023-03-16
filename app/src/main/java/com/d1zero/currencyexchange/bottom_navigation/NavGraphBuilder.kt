package com.d1zero.currencyexchange.bottom_navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.d1zero.currencyexchange.api.CurrencyApiImpl
import com.d1zero.currencyexchange.screens.CurrencyList
import com.d1zero.currencyexchange.screens.Trade

fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(startDestination = "main", route = BottomItem.Screen1.route) {
        val currencyApi = CurrencyApiImpl()
        val currencies = currencyApi.getCurrencies()
        composable(route = "main") {
            CurrencyList(
                modifier = Modifier,
                currencies = currencies,
                navigateToConverter = { navController.navigate(SubNavScreen.Trade.route) }
            )
        }
        composable(SubNavScreen.Trade.route) {
            Trade()
        }
    }
}