package com.enervision.enimobile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enervision.enimobile.data.models.client.MobileClientDTOs
import com.enervision.enimobile.data.models.login.request.LoginRequest
import com.enervision.enimobile.data.models.login.request.Session
import com.enervision.enimobile.data.models.measurementdtos.MobileMeasurementValueDTODB
import com.enervision.enimobile.data.preferences.LocalPref
import com.enervision.enimobile.data.repos.IClientRepo
import com.enervision.enimobile.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clientRepo: IClientRepo, private val localPrefs: LocalPref
) : ViewModel() {


    private var _clients: MutableStateFlow<MobileClientDTOs?> = MutableStateFlow(null)
    val clients: StateFlow<MobileClientDTOs?> = _clients
    val isLoading = MutableStateFlow(false)
    val isLoginError = MutableStateFlow(false)
    private var sessionToken = ""

    fun getLocalClients() {
        viewModelScope.launch {
            clientRepo.getMobileClients("").collect { dtos ->
                _clients.value = dtos
                isLoading.value = false
                isLoginError.value = false
            }
        }


    }

    fun loginAndGetClient() {
        isLoginError.value = false
        isLoading.value = true
        viewModelScope.launch {
            getUserNameAndPassword()
            val user = username.value.ifBlank { "ebrahem" }
            val pass = password.value.ifBlank { "Pp@Ee" }
            clientRepo.login(
                LoginRequest(
                    session = Session(
                        userName = user, password = pass
                    )
                )
            ).catch {
                isLoginError.value = true
                isLoading.value = false
            }.collect {
                sessionToken = "FAMOCASESSION=${it.session?.token}"
                clientRepo.getMobileClients(sessionToken).collect { dtos ->
                    _clients.value = dtos
                    isLoading.value = false
                }
            }

        }
    }


    var username: MutableStateFlow<String> = MutableStateFlow("")
    var password: MutableStateFlow<String> = MutableStateFlow("")
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

    val baseUrl: MutableStateFlow<String> = MutableStateFlow(localPrefs.getString("base_url") ?: "")
    val port: MutableStateFlow<String> = MutableStateFlow(localPrefs.getString("port") ?: "")
    fun changeBaseUrl(baseUrl: String, port: String) {
        this.baseUrl.value = baseUrl
        this.port.value = port
        localPrefs.setString("base_url", baseUrl)
        localPrefs.setString("port", port)

    }


    fun addMeasurement(measurement: MobileMeasurementValueDTODB) {
        viewModelScope.launch {
            clientRepo.addMeasurement(measurement).collect {

            }
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


    val sendMeasure = MutableStateFlow(0)
    fun addMeasuresRemote() {
        viewModelScope.launch {
            clientRepo.addMeasurementRemote(measurementList.value, sessionToken)
                .catch {
                    sendMeasure.value = 2
                }.collect { sendMeasure.value = 1 }
        }
    }

    fun deleteMeasure(id: Int) {
        viewModelScope.launch {
            clientRepo.deleteMeasure(id).collect {

            }
        }
    }
}