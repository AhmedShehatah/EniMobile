package com.enervision.enimobile.data.models.structure


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StructureNodes(
    @SerialName("structureNodes")
    val structureNodes: List<StructureNode?>? = null
)