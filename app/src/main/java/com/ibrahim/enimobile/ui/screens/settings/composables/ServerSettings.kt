@file:OptIn(ExperimentalMaterial3Api::class)

package com.ibrahim.enimobile.ui.screens.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ibrahim.enimobile.ui.screens.home.HomeViewModel

@Composable
fun ServerSettings(homeViewModel: HomeViewModel) {
    var serverAddress by remember {
        mutableStateOf(homeViewModel.baseUrl.value)
    }
    var port by remember {
        mutableStateOf(homeViewModel.port.value)
    }
    var isSaved by remember {
        mutableStateOf(false)
    }
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
            Text(text = "Server Settings")
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = serverAddress,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                label = {
                    Text(text = "Server Address")
                },
                onValueChange = { serverAddress = it },
                leadingIcon = { Icon(Icons.Outlined.Info, contentDescription = "Person") },
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Port")
                },
                value = port, onValueChange = { port = it },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                leadingIcon = { Icon(Icons.Outlined.Build, contentDescription = "Password") },
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Button(onClick = {
                    homeViewModel.changeBaseUrl(serverAddress, port);isSaved = true
                }) {
                    Text(if (!isSaved) "Save Changes" else "Saved!")
                }
                Spacer(modifier = Modifier.width(10.dp))
                if (isSaved)
                    Text("Saved Please Restart the app so actions can take place")
            }

        }
    }
}