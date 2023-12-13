package com.enervision.enimobile.ui.screens.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enervision.enimobile.HomeViewModel

@Composable
fun LoadingError(homeViewModel: HomeViewModel) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Failed To Login")
        Row {

            OutlinedButton(onClick = { homeViewModel.getLocalClients() }) {
                Text(text = "Load Local Data")
            }
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedButton(onClick = { homeViewModel.loginAndGetClient() }) {
                Text(text = "Try Again")
            }
        }
    }
}