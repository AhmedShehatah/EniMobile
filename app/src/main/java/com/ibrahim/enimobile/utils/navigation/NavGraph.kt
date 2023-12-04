package com.ibrahim.enimobile.utils.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ibrahim.enimobile.ui.screens.home.HomeScreen
import com.ibrahim.enimobile.HomeViewModel
import com.ibrahim.enimobile.ui.screens.settings.SettingsScreen

@Composable
fun SetupNabGraph(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(homeViewModel = homeViewModel, navHostController)
        }
        composable(
            route = Screen.SettingsScreen.route
        ) {
            SettingsScreen(navHostController, homeViewModel)
        }
    }
}