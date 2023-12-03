package com.ibrahim.enimobile.data.models.client


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Measurement(
    @SerialName("evalType")
    val evalType: String? = null,
    @PrimaryKey
    @SerialName("id")
    val id: String,
    @SerialName("lastDate")
    val lastDate: String? = null,
    @SerialName("lastValue")
    val lastValue: Int? = null,
    @SerialName("unit")
    val unit: String? = null
)