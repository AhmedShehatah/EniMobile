package com.enervision.enimobile.data.remote

import com.enervision.enimobile.data.models.client.MobileClientDTOs
import com.enervision.enimobile.data.models.login.request.LoginRequest
import com.enervision.enimobile.data.models.login.response.LoginResopnse
import com.enervision.enimobile.data.models.measurementdtos.MeasurementDTOs
import com.enervision.enimobile.data.models.structure.StructureNodes
import com.enervision.enimobile.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @GET(Constants.MOBILE_CLIENTS)
    suspend fun getMobileClients(@Header("cookie") token: String): MobileClientDTOs

    @GET(Constants.STRUCTURE_NODES)
    suspend fun getStructureNodes(): StructureNodes

    @POST(Constants.LOGIN)

    suspend fun login(@Body request: LoginRequest): LoginResopnse

    @POST(Constants.MEASUREMENTS)
    suspend fun addMeasurement(
        @Body request: MeasurementDTOs, @Header("cookie") token: String,
    ): MeasurementDTOs


}