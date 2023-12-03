@file:OptIn(ExperimentalMaterial3Api::class)

package com.ibrahim.enimobile.ui.screens.home.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ibrahim.enimobile.data.models.client.MobileClientDTO

@Composable
fun ClientItem(
    item: MobileClientDTO
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var measurement by remember {
        mutableStateOf("")
    }

    Card(
        Modifier
            .padding(10.dp)
            .clickable { isExpanded = !isExpanded }

    ) {

        Row(
            Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.dataUnitName ?: "No Name")
            Text(text = item.interval ?: "No Interval")
            Icon(
                if (isExpanded) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown,
                contentDescription = null
            )
        }
        AnimatedVisibility(visible = isExpanded) {
            Column(Modifier.padding(15.dp)) {
                Text(text = "Meter Reading (KWh)")
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = measurement,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Decimal
                    ),
                    label = {
                        Text(text = "measurement")
                    },
                    onValueChange = { measurement = it },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.AccountBox,
                            contentDescription = "measurement"
                        )
                    },
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Save")
                }
            }
        }
    }
}