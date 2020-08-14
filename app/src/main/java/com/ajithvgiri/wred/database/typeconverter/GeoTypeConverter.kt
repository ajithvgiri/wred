package com.ajithvgiri.wred.database.typeconverter

import androidx.room.TypeConverter
import com.ajithvgiri.wred.database.model.Geo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class GeoTypeConverter {

    @TypeConverter
    fun toJson(value: Geo): String {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Geo> = moshi.adapter(Geo::class.java)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun fromJson(value: String): Geo? {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Geo> = moshi.adapter(Geo::class.java)
        return adapter.fromJson(value)
    }
}