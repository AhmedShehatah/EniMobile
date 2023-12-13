package com.enervision.enimobile.data.repos

import com.enervision.enimobile.data.models.client.MobileClientDTOs
import com.enervision.enimobile.data.models.login.request.LoginRequest
import com.enervision.enimobile.data.models.login.response.LoginResopnse
import com.enervision.enimobile.data.models.measurementdtos.MobileMeasurementValueDTO
import com.enervision.enimobile.data.models.measurementdtos.MobileMeasurementValueDTODB
import com.enervision.enimobile.data.models.structure.StructureNodes
import kotlinx.coroutines.flow.Flow

interface IClientRepo {

    fun getMobileClients(token: String): Flow<MobileClientDTOs>

    fun getStructureNodes(): Flow<StructureNodes>

    fun login(request: LoginRequest): Flow<LoginResopnse>

    fun addMeasurement(measurement: MobileMeasurementValueDTODB): Flow<Long>
    fun addMeasurementRemote(
        measurement: List<MobileMeasurementValueDTODB>, token: String
    ): Flow<MobileMeasurementValueDTO>

    fun getAllMeasurements(): Flow<List<MobileMeasurementValueDTODB>>

    fun deleteMeasure(id: Int): Flow<Unit>
}