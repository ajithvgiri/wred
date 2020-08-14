package com.ajithvgiri.wred.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        val root = inflater.inflate(R.layout.fragment_employee_list, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        employeeListViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}