package com.ajithvgiri.wred.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ajithvgiri.wred.R

class EmployeeListFragment : Fragment() {

    private lateinit var employeeListViewModel: EmployeeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        employeeListViewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_employee_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        employeeListViewModel.employeeList.observe(viewLifecycleOwner, Observer {listOfEmployees->
            if (listOfEmployees.isNotEmpty()){
                println("employee list from offline database $listOfEmployees")
            }
        })
    }
}