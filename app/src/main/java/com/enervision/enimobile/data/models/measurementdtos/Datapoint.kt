package com.enervision.enimobile.data.models.measurementdtos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Datapoint(
    @PrimaryKey val id: Int? = null,
    val type: String? = null,
)