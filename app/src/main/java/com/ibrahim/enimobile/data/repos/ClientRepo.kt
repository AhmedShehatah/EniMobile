package com.ibrahim.enimobile.data.repos

import com.ibrahim.enimobile.data.local.AppDatabase
import com.ibrahim.enimobile.data.models.client.Measurement
import com.ibrahim.enimobile.data.models.client.MobileClientDTOs
import com.ibrahim.enimobile.data.models.login.request.LoginRequest
import com.ibrahim.enimobile.data.models.login.response.LoginResopnse
import com.ibrahim.enimobile.data.models.structure.StructureNodes
import com.ibrahim.enimobile.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClientRepo(private val apiService: ApiService, private val db: AppDatabase) : IClientRepo {
    override fun getMobileClients(): Flow<MobileClientDTOs> = flow {
        try {
            val response = apiService.getMobileClients()
            for (item in response.mobileClientDTOs ?: emptyList()) {
                db.getDao().addClient(item ?: throw IllegalAccessError())
            }
            emit(response)
        } catch (e: Exception) {
            val cachedResponse =
                db.getDao().getAllClients()
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

    override fun addMeasurement(measurement: Measurement): Flow<Long> = flow {
        val response = db.getDao().addMeasurement(measurement)
        emit(response)
    }
}