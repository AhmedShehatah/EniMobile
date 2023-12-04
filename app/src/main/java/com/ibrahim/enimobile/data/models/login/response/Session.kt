package com.ibrahim.enimobile.data.models.login.response


import com.squareup.moshi.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class Session(
    val clientViewActive: Boolean? = false,
    val currentClient: String? = "",
    val currentRootStrNode: String? = "",
    val enterClientView: String? = "",
    val leaveClientView: Boolean? = false,
    val password: String? = "",
    val token: String? = "",
    @Json(name = "user_ID")
    val userID: String? = "",
    @Json(name = "user_Name")
    val userName: String? = "",
    val validUntil: String? = ""
)