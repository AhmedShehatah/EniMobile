@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.ibrahim.enimobile.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ibrahim.enimobile.HomeViewModel
import com.ibrahim.enimobile.data.models.client.MobileClientDTO
import com.ibrahim.enimobile.data.models.client.MobileClientDTOs
import com.ibrahim.enimobile.ui.screens.home.composables.ClientItem
import com.ibrahim.enimobile.ui.screens.home.composables.FilterTree
import com.ibrahim.enimobile.ui.screens.home.composables.Measurements

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navHostController: NavHostController) {
    var clients by remember {
        mutableStateOf(MobileClientDTOs())
    }
    var searchText by remember { mutableStateOf("") }
    var filteredItems = remember(searchText) {
        clients.mobileClientDTOs?.filter { item ->
            item?.dataUnitName?.contains(searchText, ignoreCase = true) ?: true
        }
    }
    var isEnabled by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true) {
        homeViewModel.clients.collect {
            clients = it ?: MobileClientDTOs(emptyList())
        }
    }
    if (homeViewModel.isLoading.collectAsState().value) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {


        Box {
            Column {

                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Blue.copy(0.5f))
                        .padding(10.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable { isEnabled = !isEnabled },
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = " ${filteredItems?.size ?: clients.mobileClientDTOs?.size ?: 0}/${clients.mobileClientDTOs?.size ?: 0} ")
                        Icon(
                            if (isEnabled) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            null
                        )
                    }
                    if (isEnabled) {
                        Row(Modifier.padding(10.dp)) {
                            TextField(
                                value = searchText,
                                onValueChange = { searchText = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .clip(MaterialTheme.shapes.medium)
                                    .background(MaterialTheme.colorScheme.background)
                                    .padding(8.dp),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                                ),
                                placeholder = { Text(text = "Search...") },
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    textColor = MaterialTheme.colorScheme.onSurface
                                )
                            )

                        }
                        FilterTree()
                    }
                }

                if (filteredItems == null) filteredItems = clients.mobileClientDTOs
                LazyColumn(
                    Modifier.fillMaxSize()
                ) {
                    items(filteredItems ?: emptyList()) {
                        ClientItem(item = it ?: MobileClientDTO(""), homeViewModel)
                    }
                }
            }

            Measurements(Modifier.align(Alignment.BottomCenter), homeViewModel)
        }

    }

}