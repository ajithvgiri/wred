package com.ajithvgiri.wred.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajithvgiri.wred.database.typeconverter.AddressTypeConverter
import com.ajithvgiri.wred.database.typeconverter.CompanyTypeConverter
import com.ajithvgiri.wred.database.typeconverter.GeoTypeConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
@Entity(tableName = "employees")
data class Employee(
    @Json(name = "id")
    @PrimaryKey
    val id: String,

    @Json(name = "name")
    var name: String? = null,

    @Json(name = "username")
    var username: String? = null,

    @Json(name = "email")
    var email: String? = null,

    @Json(name = "profile_image")
    var profile_image: String? = null,

    @Json(name = "address")
    @TypeConverters(AddressTypeConverter::class)
    var address: Address = Address(),

    @Json(name = "phone")
    var phone: String? = null,

    @Json(name = "website")
    var website: String? = null,

    @Json(name = "company")
    @TypeConverters(CompanyTypeConverter::class)
    var company: Company? = Company()
):Serializable

@JsonClass(generateAdapter = true)
data class Address(
    var street: String? = null,

    var suite: String? = null,

    var city: String? = null,

    var zipcode: String? = null,

    @TypeConverters(GeoTypeConverter::class)
    var geo: Geo = Geo()
):Serializable

@JsonClass(generateAdapter = true)
data class Geo(var lat: String ? = null, var lng: String ? = null):Serializable

@JsonClass(generateAdapter = true)
data class Company(var name: String? = null, var catchPhrase: String? = null, var bs: String? = null):Serializable