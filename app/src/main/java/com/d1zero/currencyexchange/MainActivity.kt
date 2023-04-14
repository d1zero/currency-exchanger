package com.d1zero.currencyexchange

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.d1zero.currencyexchange.bottom_navigation.BottomNavigation
import com.d1zero.currencyexchange.bottom_navigation.MainScreen
import com.d1zero.currencyexchange.bottom_navigation.NavGraph
import com.d1zero.currencyexchange.database.CurrencyDatabase
import com.d1zero.currencyexchange.database.CurrencyViewModel
import com.d1zero.currencyexchange.ui.theme.CurrencyExchangeTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            CurrencyDatabase::class.java,
            "currencies.db"
        ).build()
    }
    private val viewModel by viewModels<CurrencyViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CurrencyViewModel(db.dao) as T
                }
            }
        }
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyExchangeTheme {
                val state by viewModel.state.collectAsState()
                val navController = rememberNavController()
//                Scaffold(bottomBar = { BottomNavigation(navController = navController) }) {
//                    NavGraph(
//                        navHostController = navController,
//                        state = state,
//                        onEvent = viewModel::onEvent
//                    )
//                }
                Scaffold(bottomBar = { BottomNavigation(navController = navController) }) {
                    it.calculateBottomPadding()
                    NavGraph(
                        navHostController = navController,
                        state = state,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}
