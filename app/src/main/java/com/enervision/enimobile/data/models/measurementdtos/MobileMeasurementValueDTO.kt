package com.enervision.enimobile.data.models.measurementdtos

import androidx.room.Entity
import androidx.room.PrimaryKey


data class MobileMeasurementValueDTO(
    val datapoint: Datapoint? = null,
    val rawValue: Int? = null,
    val readOutDate: String? = null,
)

@Entity
data class MobileMeasurementValueDTODB(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val datapoint: Datapoint? = null,
    val rawValue: Int? = null,
    val readOutDate: String? = null,
)

fun MobileMeasurementValueDTODB.convertToRemoteModel(): MobileMeasurementValueDTO {
    return MobileMeasurementValueDTO(
        datapoint, rawValue, readOutDate
    )
}
