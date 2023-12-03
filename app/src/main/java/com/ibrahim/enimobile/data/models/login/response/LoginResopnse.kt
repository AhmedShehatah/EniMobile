package com.ibrahim.enimobile.data.models.login.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResopnse(
    @SerialName("session")
    val session: Session? = null
)