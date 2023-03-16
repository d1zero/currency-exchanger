package com.d1zero.currencyexchange.bottom_navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.d1zero.currencyexchange.dto.Currency
import com.d1zero.currencyexchange.screens.CurrencyList
import com.d1zero.currencyexchange.screens.Graphic
import com.d1zero.currencyexchange.screens.History
import com.d1zero.currencyexchange.screens.Trade

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = BottomItem.Screen1.route) {
        mainGraph(navHostController)
        historyGraph(navHostController)
        graphicsGraph(navHostController)
    }
}