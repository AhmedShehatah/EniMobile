package com.enervision.enimobile.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.enervision.enimobile.data.models.client.MobileClientDTO
import com.enervision.enimobile.data.models.measurementdtos.MobileMeasurementValueDTODB
import com.enervision.enimobile.data.models.structure.StructureNode
import com.enervision.enimobile.utils.Converters

@Database(
    entities = [
        MobileClientDTO::class,
        StructureNode::class,
        MobileMeasurementValueDTODB::class,
    ],
    version = 7,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): AppDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, "appDatabase.db"
        ).fallbackToDestructiveMigration().build()
    }
}