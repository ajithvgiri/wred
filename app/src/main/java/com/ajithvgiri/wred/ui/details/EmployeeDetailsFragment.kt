package com.ajithvgiri.wred.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ajithvgiri.wred.R

class EmployeeDetailsFragment : Fragment() {

    private lateinit var employeeDetailsViewModel: EmployeeDetailsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        employeeDetailsViewModel = ViewModelProvider(this).get(EmployeeDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_employee_details, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        employeeDetailsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}