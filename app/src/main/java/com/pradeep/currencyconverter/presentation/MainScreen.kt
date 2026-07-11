package com.pradeep.currencyconverter.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pradeep.currencyconverter.presentation.navigation.Screen
import com.pradeep.currencyconverter.presentation.navigation.bottomNavItems
import com.pradeep.currencyconverter.presentation.home.HomeScreen
import com.pradeep.currencyconverter.ui.theme.WiseDarkGreen
import com.pradeep.currencyconverter.ui.theme.WiseLimeGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val isDarkTheme = isSystemInDarkTheme()

    val topBarContainerColor = if (isDarkTheme) MaterialTheme.colorScheme.surface else WiseDarkGreen
    val topBarContentColor = if (isDarkTheme) MaterialTheme.colorScheme.onSurface else Color.White

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    val title = when {
                        currentDestination?.hierarchy?.any { it.route == Screen.Home.route } == true -> Screen.Home.title
                        currentDestination?.hierarchy?.any { it.route == Screen.Settings.route } == true -> Screen.Settings.title
                        else -> "FX Converter"
                    }
                    Text(
                        text = title,
                        color = WiseLimeGreen,
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topBarContainerColor,
                    titleContentColor = topBarContentColor,
                    navigationIconContentColor = topBarContentColor
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                bottomNavItems.forEach { item ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == item.route } == true
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.name) },
                        label = { Text(item.name) },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(
                                    navController.graph.findStartDestination().id
                                ) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Settings.route) {
                // TODO Placeholder for Settings Screen
                Text("Settings Screen")
            }
        }
    }
}
