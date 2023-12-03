package com.ibrahim.enimobile.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ibrahim.enimobile.ui.screens.home.HomeViewModel
import com.ibrahim.enimobile.ui.screens.settings.composables.ServerSettings
import com.ibrahim.enimobile.ui.screens.settings.composables.UserSettingsCard

@Composable
fun SettingsScreen(navHostController: NavHostController, homeViewModel: HomeViewModel) {

    Column(Modifier.verticalScroll(rememberScrollState())) {
        UserSettingsCard(homeViewModel = homeViewModel)
        Spacer(modifier = Modifier.height(20.dp))
        ServerSettings(homeViewModel)
    }


}