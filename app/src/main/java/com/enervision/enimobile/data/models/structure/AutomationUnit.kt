package com.enervision.enimobile.data.models.structure


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class AutomationUnit(
    @PrimaryKey
    @SerialName("id")
    val id: Int? = null,
    @SerialName("type")
    val type: String? = null
)