package com.ibrahim.enimobile.data.models.login.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("session")
    val session: Session? = null
)