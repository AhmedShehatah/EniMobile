package com.ibrahim.enimobile.data.models.login.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Session(
    @SerialName("clientViewActive")
    val clientViewActive: Boolean? = null,
    @SerialName("currentClient")
    val currentClient: String? = null,
    @SerialName("currentRootStrNode")
    val currentRootStrNode: String? = null,
    @SerialName("enterClientView")
    val enterClientView: String? = null,
    @SerialName("leaveClientView")
    val leaveClientView: Boolean? = null,
    @SerialName("password")
    val password: String? = null,
    @SerialName("token")
    val token: String? = null,
    @SerialName("user_ID")
    val userID: String? = null,
    @SerialName("user_Name")
    val userName: String? = null,
    @SerialName("validUntil")
    val validUntil: String? = null
)