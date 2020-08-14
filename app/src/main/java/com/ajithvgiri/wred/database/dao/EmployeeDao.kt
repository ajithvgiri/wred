package com.ajithvgiri.wred.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajithvgiri.wred.database.model.Employee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employee: List<Employee>)

    @Query("SELECT * FROM employees")
    suspend fun getEmployees(): List<Employee>

}