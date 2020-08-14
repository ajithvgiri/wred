package com.ajithvgiri.wred.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.ajithvgiri.wred.R
import com.ajithvgiri.wred.utils.loadProfile
import kotlinx.android.synthetic.main.fragment_employee_details.*

class EmployeeDetailsFragment : Fragment() {

    private val args: EmployeeDetailsFragmentArgs by navArgs()

    private lateinit var employeeDetailsViewModel: EmployeeDetailsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        employeeDetailsViewModel = ViewModelProvider(this).get(EmployeeDetailsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_employee_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewProfile.loadProfile(args.employee?.profile_image)
        textViewName.text = "${args.employee?.name}"
        textViewUsername.text = "@${args.employee?.username}"
        textViewEmail.text = "${args.employee?.email}"
        textViewPhone.text = "${args.employee?.phone}"
        textViewWebSite.text = "${args.employee?.website}"
        textViewAddress.text = "${args.employee?.address?.suite}, ${args.employee?.address?.street} \n ${args.employee?.address?.city}, ${args.employee?.address?.zipcode}"
        textViewCompany.text = "${args.employee?.company?.name}, ${args.employee?.company?.catchPhrase} \n ${args.employee?.company?.bs}"
    }
}