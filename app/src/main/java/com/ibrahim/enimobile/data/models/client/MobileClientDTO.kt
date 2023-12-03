package com.ibrahim.enimobile.data.models.client


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class MobileClientDTO(
    @PrimaryKey
    @SerialName("dataUnitID")
    val dataUnitID: String,
    @SerialName("dataUnitName")
    val dataUnitName: String? = null,
    @SerialName("interval")
    val interval: String? = null,
    @SerialName("measurements")
    val measurements: List<Measurement?>? = null,
    @SerialName("medium")
    val medium: String? = null,
    @SerialName("meterIdent")
    val meterIdent: String? = null,
    @SerialName("primaryMeasurement")
    val primaryMeasurement: String? = null,
    @SerialName("structureNodeID")
    val structureNodeID: String? = null
)