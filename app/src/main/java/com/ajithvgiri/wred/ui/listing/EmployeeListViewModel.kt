package com.ajithvgiri.wred.ui.listing

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.ajithvgiri.wred.database.model.Employee
import com.ajithvgiri.wred.database.repository.EmployeeRepository
import com.ajithvgiri.wred.networking.ApiInterface
import com.ajithvgiri.wred.networking.RetrofitService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmployeeListViewModel(application: Application) : AndroidViewModel(application) {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _employeeList = MutableLiveData<List<Employee>>()
    val employeeList: LiveData<List<Employee>> = _employeeList

    // Retrofit
    val api: ApiInterface = RetrofitService(application).createService(ApiInterface::class.java)
    // Repository
    val repository:EmployeeRepository = EmployeeRepository(application)

    init {
        getEmployeeDataFromServer()
        getEmployeeList()
    }

    private fun getEmployeeDataFromServer(){
        val callBack = object : Callback<List<Employee>> {

            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                _loading.value = false
                Log.e("error ",t.localizedMessage)
            }

            override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>){
                if (response.isSuccessful) {
                    response.body()?.let {listOfEmployees->
                        insertEmployeeData(listOfEmployees)
                    }
                } else {
                    _loading.value = false
                }
            }
        }
        api.getEmployeeData().enqueue(callBack)
    }

    fun insertEmployeeData(listOfEmployees: List<Employee>) {
        viewModelScope.launch {
            repository.insert(listOfEmployees)
        }.invokeOnCompletion {
            _loading.value = false
        }
    }

    fun getEmployeeList(){
        _loading.value = true
        viewModelScope.launch {
            _employeeList.value = repository.getEmployeeList()
        }.invokeOnCompletion {
            _loading.value = false
        }
    }
}