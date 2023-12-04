package com.ibrahim.enimobile.data.models.client


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Locale

@Entity
data class Measurement(
    val evalType: String? = null,
    @PrimaryKey
    val id: String,
    val lastDate: String? = null,
    val lastValue: Double? = null,
    val unit: String? = null
) {
    val formattedLastDate: String?
        get() {
            // Check if lastDate is not null and not empty before formatting
            return lastDate?.takeIf { it.isNotEmpty() }?.let {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date = inputFormat.parse(it)
                outputFormat.format(date)
            }
        }
}