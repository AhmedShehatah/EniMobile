@file:OptIn(ExperimentalMaterial3Api::class)

package com.ibrahim.enimobile.ui.screens.home.composables

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ibrahim.enimobile.HomeViewModel
import com.ibrahim.enimobile.data.models.client.MobileClientDTO
import com.ibrahim.enimobile.data.models.measurementdtos.Datapoint
import com.ibrahim.enimobile.data.models.measurementdtos.MobileMeasurementValueDTODB
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ClientItem(
    item: MobileClientDTO, homeViewModel: HomeViewModel
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var measurement by remember {
        mutableStateOf(item.measurements?.get(0)?.lastValue.toString())
    }

    Card(
        Modifier
            .padding(10.dp)
            .clickable { isExpanded = !isExpanded }
            .clipToBounds()
            .graphicsLayer {

                translationX = 3.dp.toPx()
                translationY = -3.dp.toPx()


            }
            .border(
                BorderStroke(3.dp, cardColors(item.medium ?: "default")), shape = RectangleShape
            )

    ) {


        Row(
            Modifier
                .padding(15.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = item.dataUnitName ?: "No Name")
            Row {
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = item.interval ?: "No Interval")
                    Text(text = item.measurements?.get(0)?.formattedLastDate ?: "No Date")
                }
                Icon(
                    if (isExpanded) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown,
                    contentDescription = null
                )
            }

        }

        AnimatedVisibility(visible = isExpanded) {
            Column(Modifier.padding(15.dp)) {
                Text(
                    text = item.medium ?: "",
                    modifier = Modifier
                        .background(cardColors(item.medium ?: "default"))
                        .padding(3.dp)
                )
                Text(text = "Meter Reading (${item.measurements?.get(0)?.unit ?: "KWh"})")
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = measurement,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done, keyboardType = KeyboardType.Decimal
                    ),
                    label = {
                        Text(text = "measurement")
                    },
                    onValueChange = { measurement = it },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.AccountBox, contentDescription = "measurement"
                        )
                    },
                )
                Spacer(modifier = Modifier.height(10.dp))
                val context = LocalContext.current
                Button(onClick = {
                    val dateFormat =
                        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                    dateFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")

                    val old = item.measurements?.get(0)
                    val measure: MobileMeasurementValueDTODB = MobileMeasurementValueDTODB(
                        id = 0,
                        readOutDate = dateFormat.format(Date()),
                        datapoint = Datapoint(old?.id?.toInt(), type = "measurement"),
                        rawValue = measurement.toDouble().toInt()
                    )
                    homeViewModel.addMeasurement(measure)
                    Toast.makeText(context, "Saved Successfully", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Save")
                }
            }
        }
    }
}


private fun cardColors(type: String): Color {
    val map = mapOf<String, Color>(
        "Elektrizität" to Color(118, 250, 124, 255),
        "Abstrakt" to Color(247, 102, 30, 255),
        "Gas" to Color(234, 218, 82, 255),
        "Wasser" to Color(12, 152, 233, 255),
        "default" to Color.LightGray
    )
    return map[type] ?: Color.LightGray

}
