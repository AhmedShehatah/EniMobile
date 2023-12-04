package com.ibrahim.enimobile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.enimobile.data.models.client.Measurement
import com.ibrahim.enimobile.data.models.client.MobileClientDTOs
import com.ibrahim.enimobile.data.models.login.request.LoginRequest
import com.ibrahim.enimobile.data.models.login.request.Session
import com.ibrahim.enimobile.data.models.measurementdtos.MobileMeasurementValueDTO
import com.ibrahim.enimobile.data.models.measurementdtos.MobileMeasurementValueDTODB
import com.ibrahim.enimobile.data.preferences.LocalPref
import com.ibrahim.enimobile.data.repos.IClientRepo
import com.ibrahim.enimobile.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clientRepo: IClientRepo, private val localPrefs: LocalPref
) : ViewModel() {


    private var _clients: MutableStateFlow<MobileClientDTOs?> = MutableStateFlow(null)
    val clients: StateFlow<MobileClientDTOs?> = _clients
    val isLoading = MutableStateFlow(false)
    private var sessionToken = ""
    fun loginAndGetClient() {
        isLoading.value = true
        viewModelScope.launch {
            getUserNameAndPassword()

            clientRepo.login(
                LoginRequest(
                    session = Session(
                        userName = username.value, password = password.value
                    )
                )
            ).collect {
                sessionToken = "FAMOCASESSION=${it.session?.token}"
                clientRepo.getMobileClients(sessionToken).collect { dtos ->
                    Log.d("shehatah", dtos.mobileClientDTOs.toString())
                    _clients.value = dtos
                    isLoading.value = false
                }
            }

        }
    }

    var username: MutableStateFlow<String> = MutableStateFlow("ebrahem")
    var password: MutableStateFlow<String> = MutableStateFlow("Pp@Ee")
    private fun getUserNameAndPassword() {
        if (localPrefs.getString("user").isNullOrBlank()) {
            localPrefs.setString("user", username.value)
            if (localPrefs.getString("pass").isNullOrBlank()) {
                localPrefs.setString("pass", password.value)
            }
            return
        }
        username.value = localPrefs.getString("user")!!
        password.value = localPrefs.getString("pass")!!


    }

    fun saveUserNameAndPassword(username: String, password: String) {
        this.username.value = username
        this.password.value = password
        localPrefs.setString("user", username)
        localPrefs.setString("pass", password)
    }

    fun getStructureNodes() {
        viewModelScope.launch {

            clientRepo.getStructureNodes().collect {}
        }
    }

    val baseUrl: MutableStateFlow<String> =
        MutableStateFlow(localPrefs.getString("base_url") ?: Constants.BASE_URL)
    val port: MutableStateFlow<String> = MutableStateFlow(localPrefs.getString("port") ?: "")
    fun changeBaseUrl(baseUrl: String, port: String) {
        this.baseUrl.value = baseUrl
        this.port.value = port
        localPrefs.setString("base_url", baseUrl)
        localPrefs.setString("port", port)

    }


    fun addMeasurement(measurement: MobileMeasurementValueDTODB) {
        viewModelScope.launch {
            clientRepo.addMeasurement(measurement).collect {}
        }
    }

    val measurementList: MutableStateFlow<List<MobileMeasurementValueDTODB>> =
        MutableStateFlow(emptyList())

    fun getAllMeasurements() {

        viewModelScope.launch {
            clientRepo.getAllMeasurements().collect {
                measurementList.value = it
            }
        }
    }


    fun addMeasuresRemote() {
        viewModelScope.launch {
            clientRepo.addMeasurementRemote(measurementList.value, sessionToken).collect {}
        }
    }
}