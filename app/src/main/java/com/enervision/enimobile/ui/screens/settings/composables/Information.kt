package com.enervision.enimobile.ui.screens.settings.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Information() {

    Card(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)

        ) {
            Text(text = "Information")
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Information")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Information")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Information")
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}