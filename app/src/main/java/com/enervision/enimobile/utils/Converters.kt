package com.enervision.enimobile.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.enervision.enimobile.data.models.client.Measurement
import com.enervision.enimobile.data.models.measurementdtos.Datapoint
import com.enervision.enimobile.data.models.structure.AutomationUnit
import com.enervision.enimobile.data.models.structure.DataUnit
import java.util.Collections

class Converters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromStringToList(data: String?): List<Measurement> {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType = object : TypeToken<List<Measurement>>() {

            }.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromListToString(myObjects: List<Measurement>): String {
            val gson = Gson()
            return gson.toJson(myObjects)
        }


        @TypeConverter
        @JvmStatic
        fun fromStringToUnitDataList(data: String?): List<DataUnit> {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType = object : TypeToken<List<DataUnit>>() {

            }.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromUnitDataListToString(myObjects: List<DataUnit>): String {
            val gson = Gson()
            return gson.toJson(myObjects)
        }


        @TypeConverter
        @JvmStatic
        fun fromStringToAutomationUnitList(data: String?): List<AutomationUnit> {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType = object : TypeToken<List<AutomationUnit>>() {

            }.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromAutomationUnitListToString(myObjects: List<AutomationUnit>): String {
            val gson = Gson()
            return gson.toJson(myObjects)
        }

        @TypeConverter
        @JvmStatic
        fun fromStringToIntList(data: String?): List<Int> {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType = object : TypeToken<List<Int>>() {

            }.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromIntListToString(myObjects: List<Int>?): String {
            val gson = Gson()
            return gson.toJson(myObjects)
        }

        @TypeConverter
        @JvmStatic
        fun fromDatapoint(datapoint: Datapoint?): String? {
            return Gson().toJson(datapoint)
        }

        @TypeConverter
        @JvmStatic
        fun toDatapoint(datapointString: String?): Datapoint? {
            if (datapointString == null) {
                return null
            }
            val type = object : TypeToken<Datapoint>() {}.type
            return Gson().fromJson(datapointString, type)
        }
    }


}