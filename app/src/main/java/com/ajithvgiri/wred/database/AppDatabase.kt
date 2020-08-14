package com.ajithvgiri.wred.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ajithvgiri.wred.database.dao.EmployeeDao
import com.ajithvgiri.wred.database.model.Employee
import com.ajithvgiri.wred.database.typeconverter.AddressTypeConverter
import com.ajithvgiri.wred.database.typeconverter.CompanyTypeConverter
import com.ajithvgiri.wred.database.typeconverter.GeoTypeConverter


@Database(
    entities = [Employee::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    AddressTypeConverter::class, CompanyTypeConverter::class, GeoTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    companion object {
        private var appDatabase: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                synchronized(this) {
                    appDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "EmployeeDirectory.db"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return appDatabase!!
        }
    }
}