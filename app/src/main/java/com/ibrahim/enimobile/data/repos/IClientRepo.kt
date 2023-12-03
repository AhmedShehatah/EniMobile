package com.ibrahim.enimobile.data.repos

import com.ibrahim.enimobile.data.models.client.Measurement
import com.ibrahim.enimobile.data.models.client.MobileClientDTOs
import com.ibrahim.enimobile.data.models.login.request.LoginRequest
import com.ibrahim.enimobile.data.models.login.response.LoginResopnse
import com.ibrahim.enimobile.data.models.structure.StructureNodes
import kotlinx.coroutines.flow.Flow

interface IClientRepo {

    fun getMobileClients(): Flow<MobileClientDTOs>

    fun getStructureNodes(): Flow<StructureNodes>

    fun login(request: LoginRequest): Flow<LoginResopnse>

    fun addMeasurement(measurement: Measurement): Flow<Long>
}