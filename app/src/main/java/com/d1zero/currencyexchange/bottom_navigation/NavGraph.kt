package com.d1zero.currencyexchange.bottom_navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = BottomItem.Screen1.route) {
        mainGraph(navHostController)
        historyGraph(navHostController)
        graphicsGraph(navHostController)
    }
}