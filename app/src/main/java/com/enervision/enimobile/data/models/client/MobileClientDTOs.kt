package com.enervision.enimobile.data.models.client

import com.squareup.moshi.Json


data class MobileClientDTOs(
    @Json(name = "mobileClientDTOs")
    val mobileClientDTOs: List<MobileClientDTO?>? = null
)