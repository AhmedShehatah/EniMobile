package com.ibrahim.enimobile.data.models.client


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MobileClientDTOs(
    @SerialName("mobileClientDTOs")
    val mobileClientDTOs: List<MobileClientDTO?>? = null
)