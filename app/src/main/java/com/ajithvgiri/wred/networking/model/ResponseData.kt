package com.ajithvgiri.wred.networking.model

import com.ajithvgiri.wred.database.model.Employee
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class ResponseData(var employeesList: List<Employee>) : Serializable