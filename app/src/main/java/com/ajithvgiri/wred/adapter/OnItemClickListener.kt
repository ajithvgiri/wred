package com.ajithvgiri.wred.adapter

import android.widget.ImageView
import android.widget.TextView
import com.ajithvgiri.wred.database.model.Employee

interface OnItemClickListener {
    fun onItemClickListener(
        employee: Employee,
        imageViewProfile: ImageView,
        textViewName: TextView
    )
}