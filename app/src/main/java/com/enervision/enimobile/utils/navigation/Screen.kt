package com.enervision.enimobile.utils.navigation

sealed class Screen(val route: String) {
    data object HomeScreen : Screen(route = "/home-screen")
    data object SettingsScreen : Screen(route = "/settings-screen")
}
