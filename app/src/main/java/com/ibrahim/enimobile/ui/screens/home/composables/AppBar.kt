@file:OptIn(ExperimentalMaterial3Api::class)

package com.ibrahim.enimobile.ui.screens.home.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.ibrahim.enimobile.ui.screens.home.HomeViewModel
import com.ibrahim.enimobile.utils.navigation.Screen

@ExperimentalMaterial3Api
@Composable
fun AppTopBar(viewModel: HomeViewModel, navHostController: NavHostController) {

    var isSettings by remember {
        mutableStateOf(false)
    }
    Column {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.Blue.copy(0.3f)
            ),
            title = {
                Text("EniMobile")
            },
            navigationIcon = {
                if (isSettings) {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                        isSettings = false
                    }) {
                        Icon(Icons.Outlined.ArrowBack, null)
                    }
                }

            }, actions = {
                if (!isSettings) {
                    IconButton(onClick = {
                        viewModel.getClients()
                    }) {
                        Icon(Icons.Outlined.Refresh, null)
                    }
                    IconButton(onClick = {
                        navHostController.navigate(Screen.SettingsScreen.route)
                        isSettings = true
                    }) {
                        Icon(Icons.Outlined.Settings, null)

                    }
                }

            })

    }
}