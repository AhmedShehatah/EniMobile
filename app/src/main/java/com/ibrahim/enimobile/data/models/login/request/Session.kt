package com.ibrahim.enimobile.data.models.login.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Session(
    @SerialName("clientViewActive")
    val clientViewActive: Boolean? = false,
    @SerialName("currentClient")
    val currentClient: String? = "",
    @SerialName("currentRootStrNode")
    val currentRootStrNode: String? = "",
    @SerialName("enterClientView")
    val enterClientView: String? = "",
    @SerialName("leaveClientView")
    val leaveClientView: Boolean? = false,
    @SerialName("password")
    val password: String? = "",
    @SerialName("token")
    val token: String? = "",
    @SerialName("user_ID")
    val userID: String? = "",
    @SerialName("user_Name")
    val userName: String? = "",
    @SerialName("validUntil")
    val validUntil: String? = ""
)