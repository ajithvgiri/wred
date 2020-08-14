package com.ajithvgiri.wred.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EmployeeDetailsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Employee Details Fragment"
    }
    val text: LiveData<String> = _text
}