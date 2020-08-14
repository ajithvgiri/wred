package com.ajithvgiri.wred.database.repository

import android.content.Context
import com.ajithvgiri.wred.database.AppDatabase
import com.ajithvgiri.wred.database.dao.EmployeeDao
import com.ajithvgiri.wred.database.model.Employee

class EmployeeRepository(var application: Context) {

    //Dao
    private val employeeDao = AppDatabase.getInstance(application).employeeDao()


    suspend fun insert(listOfEmployees: List<Employee>) {
        employeeDao.insert(listOfEmployees)
    }

    suspend fun getEmployeeList() = employeeDao.getEmployees()
}