package com.ibrahim.enimobile.data.remote

import com.ibrahim.enimobile.data.models.client.Measurement
import com.ibrahim.enimobile.data.models.client.MobileClientDTOs
import com.ibrahim.enimobile.data.models.login.request.LoginRequest
import com.ibrahim.enimobile.data.models.login.response.LoginResopnse
import com.ibrahim.enimobile.data.models.structure.StructureNodes
import com.ibrahim.enimobile.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET(Constants.MOBILE_CLIENTS)
    suspend fun getMobileClients(): MobileClientDTOs

    @GET(Constants.STRUCTURE_NODES)
    suspend fun getStructureNodes(): StructureNodes

    @POST(Constants.LOGIN)
    suspend fun login(@Body request: LoginRequest): LoginResopnse

    @POST(Constants.MEASUREMENTS)
    suspend fun addMeasurement(@Body request: Measurement)
}