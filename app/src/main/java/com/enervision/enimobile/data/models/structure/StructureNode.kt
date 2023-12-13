package com.enervision.enimobile.data.models.structure


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class StructureNode(
    @SerialName("automationUnits")
    val automationUnits: List<AutomationUnit?>? = null,
    @SerialName("child_nodes")
    val childNodes: List<Int?>? = null,
    @SerialName("dataUnits")
    val dataUnits: List<DataUnit?>? = null,
    @SerialName("hideTreeView")
    val hideTreeView: Boolean? = null,
    @PrimaryKey
    @SerialName("node_ID")
    val nodeID: Int? = null,
    @SerialName("node_Name")
    val nodeName: String? = null,
    @SerialName("node_Type")
    val nodeType: Int? = null,
    @SerialName("parent_ID")
    val parentID: Int? = null,
    @SerialName("scadaPages")
    val scadaPages: List<Int?>? = null,
    @SerialName("userAuthorized")
    val userAuthorized: Boolean? = null,
    @SerialName("valueAssignments")
    val valueAssignments: List<Int?>? = null
)