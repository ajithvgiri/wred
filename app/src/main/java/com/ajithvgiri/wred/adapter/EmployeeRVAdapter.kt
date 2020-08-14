package com.ajithvgiri.wred.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.ajithvgiri.wred.R
import com.ajithvgiri.wred.database.model.Employee
import com.ajithvgiri.wred.utils.loadProfile
import kotlinx.android.synthetic.main.layout_recyclerview_employee.view.*
import java.util.*
import kotlin.collections.ArrayList

class EmployeeRVAdapter(
    var listOfEmployees: List<Employee>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<EmployeeRVAdapter.ActivitiesViewHolder>(),
    Filterable {

    var filteredListOfEmployees=ArrayList<Employee>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recyclerview_employee, parent, false)
        return ActivitiesViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return filteredListOfEmployees.size
    }

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        holder.bind(filteredListOfEmployees[position])
    }

    class ActivitiesViewHolder(
        itemView: View,
        private var onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(employee: Employee) {
            itemView.textViewName.text = "${employee.name}"
            itemView.textViewCompany.text = "${employee.company?.name}"
            itemView.imageViewProfile.loadProfile(employee.profile_image)

            ViewCompat.setTransitionName(itemView.imageViewProfile, "${employee.id}ImageView")
            ViewCompat.setTransitionName(itemView.textViewName, "${employee.id}TextView")

            itemView.setOnClickListener {
                onItemClickListener.onItemClickListener(employee,itemView.imageViewProfile,itemView.textViewName)
            }
        }
    }

    // Search Filter
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults? {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    filteredListOfEmployees = listOfEmployees as ArrayList<Employee>
                } else {
                    val filteredList: MutableList<Employee> = ArrayList()
                    for (employee in listOfEmployees) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or email match
                        val name = employee.name ?: ""
                        val email = employee.email ?: ""
                        if (name.toLowerCase(Locale.getDefault()).contains(charString.toLowerCase(Locale.getDefault()))
                            || email.toLowerCase(Locale.getDefault()).contains(charSequence)) {
                            filteredList.add(employee)
                        }
                    }
                    filteredListOfEmployees = filteredList as ArrayList<Employee>
                }
                val filterResults = FilterResults()
                filterResults.values = filteredListOfEmployees
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                filteredListOfEmployees = filterResults.values as ArrayList<Employee>
                notifyDataSetChanged()
            }
        }
    }
}