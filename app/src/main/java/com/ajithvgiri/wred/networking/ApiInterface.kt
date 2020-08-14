package com.ajithvgiri.wred.networking


import com.ajithvgiri.wred.database.model.Employee
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    // getting employee details
    @GET("5d565297300000680030a986")
    fun getEmployeeData(): Call<List<Employee>>
}