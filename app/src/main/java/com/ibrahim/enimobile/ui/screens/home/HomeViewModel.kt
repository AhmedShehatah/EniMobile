package com.ibrahim.enimobile.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.enimobile.data.models.client.MobileClientDTOs
import com.ibrahim.enimobile.data.models.login.request.LoginRequest
import com.ibrahim.enimobile.data.models.login.request.Session
import com.ibrahim.enimobile.data.preferences.LocalPref
import com.ibrahim.enimobile.data.repos.IClientRepo
import com.ibrahim.enimobile.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clientRepo: IClientRepo,
    private val localPrefs: LocalPref
) : ViewModel() {


    private var _clients: MutableStateFlow<MobileClientDTOs?> = MutableStateFlow(null)
    val clients: StateFlow<MobileClientDTOs?> = _clients
    val isLoading = MutableStateFlow(false)
    fun getClients() {
        isLoading.value = true
        viewModelScope.launch {

            clientRepo.getMobileClients().collect {
                _clients.value = it
                isLoading.value = false
            }
        }
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


    fun login(username: String, password: String) {
        viewModelScope.launch {
            clientRepo.login(LoginRequest(Session(password = password, userName = username)))
                .collect {}
        }
    }

}