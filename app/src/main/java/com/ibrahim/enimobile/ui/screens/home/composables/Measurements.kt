package com.ibrahim.enimobile.ui.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ibrahim.enimobile.HomeViewModel

@Composable
fun Measurements(modifier: Modifier = Modifier, homeViewModel: HomeViewModel) {

    val measurementList = homeViewModel.measurementList.collectAsState().value
    LaunchedEffect(key1 = true) {
        homeViewModel.getAllMeasurements()
    }
    Row(
        modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = "Pending Measures: ${measurementList.size}")
        Button(onClick = {
            homeViewModel.addMeasuresRemote()
        }) {
            Text(text = "Send")
        }

    }

}