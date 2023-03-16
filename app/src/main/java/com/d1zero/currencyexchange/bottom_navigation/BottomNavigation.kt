package com.d1zero.currencyexchange.bottom_navigation

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(navController: NavController) {
    val listItems = listOf(
        BottomItem.Screen1,
        BottomItem.Screen2,
        BottomItem.Screen3,
    )

    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.hierarchy

        listItems.forEach { item ->
            if (currentRoute != null) {
                BottomNavigationItem(
                    selected = currentRoute.any { it.route == item.route },
                    onClick = {
                        navController.navigate(item.route)
                    },
                    icon = {
                        Icon(
                            painterResource(id = item.iconId),
                            contentDescription = "Icon"
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 9.sp
                        )
                    },
                    selectedContentColor = Color.Blue,
                    unselectedContentColor = Color.Gray
                )
            }
        }
    }
}