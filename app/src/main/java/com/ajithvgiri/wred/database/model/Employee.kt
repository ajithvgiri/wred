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
    @Json(name = "_id")
    @PrimaryKey
    val _id: String,

    @Json(name = "name")
    var name: String = "",

    @Json(name = "username")
    var username: String = "",

    @Json(name = "email")
    var email: String = "",

    @Json(name = "profile_image")
    var profile_image: String = "",

    @Json(name = "address")
    @TypeConverters(AddressTypeConverter::class)
    var address: Address = Address(),

    @Json(name = "phone")
    var phone: String = "",

    @Json(name = "website")
    var website: String = "",

    @Json(name = "company")
    @TypeConverters(CompanyTypeConverter::class)
    var company: Company = Company()
):Serializable

@JsonClass(generateAdapter = true)
data class Address(
    var street: String = "",

    var suite: String = "",

    var city: String = "",

    var zipcode: String = "",

    @TypeConverters(GeoTypeConverter::class)
    var geo: Geo = Geo()
):Serializable

@JsonClass(generateAdapter = true)
data class Geo(var lat: String = "", var lng: String = ""):Serializable

@JsonClass(generateAdapter = true)
data class Company(var name: String = "", var catchPhrase: String = "", var bs: String = ""):Serializable