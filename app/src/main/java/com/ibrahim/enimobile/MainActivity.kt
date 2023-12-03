@file:OptIn(ExperimentalMaterial3Api::class)

package com.ibrahim.enimobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ibrahim.enimobile.ui.screens.home.HomeViewModel
import com.ibrahim.enimobile.ui.screens.home.composables.AppTopBar
import com.ibrahim.enimobile.ui.theme.EniMobileTheme
import com.ibrahim.enimobile.utils.navigation.SetupNabGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navHostController = rememberNavController()
            LaunchedEffect(key1 = true) {
                viewModel.getClients()

            }
            EniMobileTheme {
                Scaffold(
                    topBar = {
                        AppTopBar(viewModel, navHostController)
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        SetupNabGraph(
                            navHostController = navHostController,
                            homeViewModel = viewModel
                        )
                    }

                }


            }
        }
    }
}



