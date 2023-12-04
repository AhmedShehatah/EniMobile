package com.ibrahim.enimobile.data.models.client


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MobileClientDTO(
    @PrimaryKey
    val dataUnitID: String,
    val dataUnitName: String? = null,
    val interval: String? = null,
    val measurements: List<Measurement?>? = null,
    val medium: String? = null,
    val meterIdent: String? = null,
    val primaryMeasurement: String? = null,
    val structureNodeID: String? = null
)