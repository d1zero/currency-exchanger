package com.d1zero.currencyexchange.bottom_navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.d1zero.currencyexchange.database.CurrencyEvent
import com.d1zero.currencyexchange.database.CurrencyState

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    state: CurrencyState,
    onEvent: (CurrencyEvent) -> Unit
) {

    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigation(navController = navController) }) {
        NavGraph(navHostController = navController, state = state, onEvent = onEvent)
    }
}