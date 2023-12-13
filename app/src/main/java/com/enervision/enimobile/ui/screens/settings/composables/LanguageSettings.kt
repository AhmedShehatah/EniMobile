package com.enervision.enimobile.ui.screens.settings.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LanguageSettings() {

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
            Text(text = "Other Settings")
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
            ) {
                Text(text = "Select Language")


                var expanded by remember { mutableStateOf(false) }
                val items = listOf("English", "Deutsch", "العربية")
                var selectedIndex by remember { mutableIntStateOf(0) }
                Box(
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopStart)
                        .padding(5.dp)
                ) {
                    Row(modifier = Modifier.clickable(onClick = { expanded = true })) {
                        Text(
                            items[selectedIndex],
                        )

                        Icon(
                            if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .background(Color.White)

                    ) {
                        items.forEachIndexed { index, s ->
                            DropdownMenuItem(
                                text = {
                                    Text(text = s)
                                },
                                onClick = {
                                    selectedIndex = index
                                    expanded = false
                                },
                            )

                        }
                    }
                }
            }
        }
    }
}