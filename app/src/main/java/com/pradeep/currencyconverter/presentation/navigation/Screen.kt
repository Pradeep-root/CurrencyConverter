package com.pradeep.currencyconverter.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object Home : Screen("home", "Home", Icons.Default.Home)
    data object Search : Screen("search", "Search", Icons.Default.Search)
    data object Settings : Screen("settings", "Settings", Icons.Default.Settings)
}

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("Home", Screen.Home.route, Screen.Home.icon),
    BottomNavItem("Search", Screen.Search.route, Screen.Search.icon),
    BottomNavItem("Settings", Screen.Settings.route, Screen.Settings.icon)
)
