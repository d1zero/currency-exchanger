package com.d1zero.currencyexchange.bottom_navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.d1zero.currencyexchange.dto.Currency
import com.d1zero.currencyexchange.screens.CurrencyList
import com.d1zero.currencyexchange.screens.Trade

fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(startDestination = "main", route = BottomItem.Screen1.route) {
        composable(route = "main") {
            CurrencyList(
                modifier = Modifier,
                currencies = listOf(
                    Currency(
                        1,
                        "RUB",
                        true
                    ),
                    Currency(
                        2,
                        "USD",
                        true
                    ),
                    Currency(
                        3,
                        "EUR",
                        true
                    ),
                ),
                navigateToConverter = { navController.navigate(SubNavScreen.Trade.route) }
            )
        }
        composable(SubNavScreen.Trade.route) {
            Trade()
        }
    }
}