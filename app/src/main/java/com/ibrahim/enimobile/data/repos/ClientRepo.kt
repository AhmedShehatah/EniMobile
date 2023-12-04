package com.ibrahim.enimobile.data.repos

import android.util.Log
import com.ibrahim.enimobile.data.local.AppDatabase
import com.ibrahim.enimobile.data.models.client.Measurement
import com.ibrahim.enimobile.data.models.client.MobileClientDTOs
import com.ibrahim.enimobile.data.models.login.request.LoginRequest
import com.ibrahim.enimobile.data.models.login.response.LoginResopnse
import com.ibrahim.enimobile.data.models.measurementdtos.MeasurementDTOs
import com.ibrahim.enimobile.data.models.measurementdtos.MobileMeasurementValueDTO
import com.ibrahim.enimobile.data.models.measurementdtos.MobileMeasurementValueDTODB
import com.ibrahim.enimobile.data.models.measurementdtos.convertToRemoteModel
import com.ibrahim.enimobile.data.models.structure.StructureNodes
import com.ibrahim.enimobile.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClientRepo(private val apiService: ApiService, private val db: AppDatabase) : IClientRepo {
    override fun getMobileClients(token: String): Flow<MobileClientDTOs> = flow {
        try {

            val response = apiService.getMobileClients(token)
            Log.d("repo here", response.mobileClientDTOs.toString())
            for (item in response.mobileClientDTOs ?: emptyList()) {
                db.getDao().addClient(item ?: throw IllegalAccessError())
            }
            emit(response)
        } catch (e: Exception) {
            Log.d("repo error here", "$e")
            val cachedResponse = db.getDao().getAllClients()
            cachedResponse.collect {
                emit(MobileClientDTOs(it))
            }
        }

    }

    override fun getStructureNodes(): Flow<StructureNodes> = flow {
        try {
            val response = apiService.getStructureNodes()
            for (item in response.structureNodes ?: emptyList()) {
                db.getDao().addStructureNode(item ?: throw IllegalAccessError())
            }
            emit(response)

        } catch (e: Exception) {
            db.getDao().getAllStructureNodes().collect {
                emit(StructureNodes(it))
            }
        }
    }

    override fun login(request: LoginRequest): Flow<LoginResopnse> = flow {
        try {
            val response = apiService.login(request)
            emit(response)
        } catch (ex: Exception) {
            error("couldn't login")
        }
    }

    override fun addMeasurement(measurement: MobileMeasurementValueDTODB): Flow<Long> = flow {
        val response = db.getDao().addMeasurement(measurement)
        emit(response)
    }

    override fun getAllMeasurements(): Flow<List<MobileMeasurementValueDTODB>> = flow {
        val response = db.getDao().getAllMeasurements()
        response.collect {
            emit(it)
        }
    }

    override fun addMeasurementRemote(
        measurement: List<MobileMeasurementValueDTODB>, token: String
    ): Flow<MobileMeasurementValueDTO> = flow {
        try {
            for (measure in measurement) {
                val response = apiService.addMeasurement(
                    MeasurementDTOs(measure.convertToRemoteModel()), token
                )
                db.getDao().deleteMeasurement(measure.id)
                emit(response.mobileMeasurementValueDTO!!)

            }
        } catch (e: Exception) {
            Log.d("remote", e.toString())
        }

    }
}