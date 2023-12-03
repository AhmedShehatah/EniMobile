package com.ibrahim.enimobile.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibrahim.enimobile.data.models.client.Measurement
import com.ibrahim.enimobile.data.models.client.MobileClientDTO
import com.ibrahim.enimobile.data.models.structure.StructureNode
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addClient(clients: MobileClientDTO): Long

    @Query("SELECT * FROM MobileClientDTO")
    fun getAllClients(): Flow<List<MobileClientDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStructureNode(node: StructureNode): Long

    @Query("SELECT * FROM StructureNode")
    fun getAllStructureNodes(): Flow<List<StructureNode>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeasurement(measurement: Measurement): Long

    @Query("SELECT * FROM Measurement")
    fun getAllMeasurements(): Flow<List<Measurement>>
}