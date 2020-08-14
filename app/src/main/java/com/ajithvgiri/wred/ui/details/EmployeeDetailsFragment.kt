package com.ajithvgiri.wred.ui.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.ajithvgiri.wred.MainActivity
import com.ajithvgiri.wred.R
import com.ajithvgiri.wred.utils.loadProfile
import kotlinx.android.synthetic.main.fragment_employee_details.*
import kotlinx.android.synthetic.main.fragment_employee_details.toolbar
import kotlinx.android.synthetic.main.fragment_employee_list.*

class EmployeeDetailsFragment : Fragment() {

    private val args: EmployeeDetailsFragmentArgs by navArgs()

    private lateinit var employeeDetailsViewModel: EmployeeDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        postponeEnterTransition()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Determine how shared elements are handled
//        sharedElementEnterTransition = TransitionInflater.from(this.context).inflateTransition(R.transition.change_bounds)
//        sharedElementReturnTransition =  TransitionInflater.from(this.context).inflateTransition(R.transition.change_bounds)

        employeeDetailsViewModel = ViewModelProvider(this).get(EmployeeDetailsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_employee_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setSupportActionBar(toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imageViewProfile.loadProfile(args.employee.profile_image)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageViewProfile.transitionName = args.transitionImageView
            textViewName.transitionName = args.transitionTextView
        }
        startPostponedEnterTransition()

        textViewName.text = "${args.employee.name}"
        textViewUsername.text = "${args.employee.username}"
        textViewEmail.text = "${args.employee.email}"
        textViewPhone.text = "${args.employee.phone}"
        textViewWebSite.text = "${args.employee.website}"
        textViewAddress.text =
            "${args.employee.address.suite}, ${args.employee.address.street} \n ${args.employee.address.city}, ${args.employee.address.zipcode}"
        textViewCompany.text =
            "${args.employee?.company?.name}, ${args.employee?.company?.catchPhrase} \n ${args.employee?.company?.bs}"
    }
}