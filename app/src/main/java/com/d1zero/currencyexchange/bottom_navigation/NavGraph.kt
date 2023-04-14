package com.d1zero.currencyexchange.bottom_navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.d1zero.currencyexchange.database.CurrencyEvent
import com.d1zero.currencyexchange.database.CurrencyState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,
    state: CurrencyState,
    onEvent: (CurrencyEvent) -> Unit
) {
    NavHost(navController = navHostController, startDestination = BottomItem.Screen1.route) {
        mainGraph(navHostController, state, onEvent)
        historyGraph(navHostController)
        graphicsGraph(navHostController)
    }
}