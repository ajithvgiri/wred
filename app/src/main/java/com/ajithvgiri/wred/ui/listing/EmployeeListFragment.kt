package com.ajithvgiri.wred.ui.listing

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajithvgiri.wred.MainActivity
import com.ajithvgiri.wred.R
import com.ajithvgiri.wred.adapter.EmployeeRVAdapter
import com.ajithvgiri.wred.adapter.OnItemClickListener
import com.ajithvgiri.wred.database.model.Employee
import kotlinx.android.synthetic.main.fragment_employee_list.*


class EmployeeListFragment : Fragment(), OnItemClickListener{

    private lateinit var employeeListViewModel: EmployeeListViewModel
    private var searchView: SearchView? = null
    private var employeesRVAdapter:EmployeeRVAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
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

        (activity as MainActivity).setSupportActionBar(toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.setTitle(R.string.app_name)

        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        employeeListViewModel.employeeList.observe(viewLifecycleOwner, Observer { listOfEmployees ->
            if (listOfEmployees.isNotEmpty()) {
                employeesRVAdapter = EmployeeRVAdapter(listOfEmployees, this)
                recyclerViewEmployees.apply {
                    layoutManager = linearLayoutManager
                    adapter = employeesRVAdapter
                }
            }
        })

        employeeListViewModel.loading.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })
    }

    override fun onItemClickListener(employee: Employee) {
//        val actions = EmployeeListFragmentD
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager?
        searchView = menu.findItem(R.id.action_search).actionView as SearchView?
        searchView?.setSearchableInfo(searchManager?.getSearchableInfo(requireActivity().componentName))
        searchView?.maxWidth = Int.MAX_VALUE

        // listening to search query text change
        searchView?.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                employeesRVAdapter?.filter?.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                employeesRVAdapter?.filter?.filter(query)
                return false
            }
        })
    }

}