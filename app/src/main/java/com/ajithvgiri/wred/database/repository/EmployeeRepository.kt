package com.ajithvgiri.wred.database.repository

import android.content.Context
import com.ajithvgiri.wred.database.AppDatabase
import com.ajithvgiri.wred.database.dao.EmployeeDao
import com.ajithvgiri.wred.database.model.Employee

class EmployeeRepository(application: Context) {

    //Dao
    private val employeeDao = AppDatabase.getInstance(application).employeeDao()

    // insert employee list to local database
    suspend fun insert(listOfEmployees: List<Employee>) {
        employeeDao.insert(listOfEmployees)
    }

    // get employee list from local database
    suspend fun getEmployeeList() = employeeDao.getEmployees()
}