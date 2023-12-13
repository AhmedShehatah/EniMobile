package com.enervision.enimobile.ui.screens.home.composables

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.enervision.enimobile.HomeViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun Measurements(modifier: Modifier = Modifier, homeViewModel: HomeViewModel) {
    var context = LocalContext.current
    val measurementList = homeViewModel.measurementList.collectAsState().value
    LaunchedEffect(key1 = true) {
        homeViewModel.getAllMeasurements()
        homeViewModel.sendMeasure.collect {
            when (it) {
                0 -> {}
                1 -> MainScope().launch {
                    Toast.makeText(
                        context, "Done Successfully", Toast.LENGTH_SHORT
                    ).show()
                }

                2 -> MainScope().launch {
                    Toast.makeText(
                        context, "Error While Uploading Data To Server", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
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