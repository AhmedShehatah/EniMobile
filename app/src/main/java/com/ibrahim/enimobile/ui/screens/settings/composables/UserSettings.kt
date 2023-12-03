package com.ibrahim.enimobile.ui.screens.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSettingsCard(
    modifier: Modifier = Modifier
        .padding(30.dp),
    homeViewModel: HomeViewModel
) {
    var userName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
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
            Text(text = "User Settings")
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = userName,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                label = {
                    Text(text = "Username")
                },
                onValueChange = { userName = it },
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "Person") },
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Password")
                },
                value = password, onValueChange = { password = it },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "Password") },
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                homeViewModel.login(userName, password)
            }) {
                Text(text = "Save Changes")
            }
        }
    }

}