package com.d1zero.currencyexchange.bottom_navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.d1zero.currencyexchange.api.CurrencyApiImpl
import com.d1zero.currencyexchange.api.TransactionApiImpl
import com.d1zero.currencyexchange.screens.*

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

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.historyGraph(navController: NavController) {
    val transactionApi = TransactionApiImpl()
    val transactions = transactionApi.getTransactions()
    composable(route = "history") {
        History(
            modifier = Modifier,
            transactions = transactions,
            navigateToConverter = { navController.navigate(SubNavScreen.Filters.route) }
        )
    }
    composable(SubNavScreen.Filters.route) {
        Filters()
    }
}

fun NavGraphBuilder.graphicsGraph(navController: NavController) {
    val currencyApi = CurrencyApiImpl()
    val currencies = currencyApi.getCurrencies()
    composable(route = "graphic") {
        Graphic(
            currencies = currencies,
        )
    }
}