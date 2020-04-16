package com.example.drumTutor

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RudimentsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val headlineText: TextView = view.findViewById(R.id.headlineText)

    var isActive: Boolean = false
        set(value) {
            field = value
            itemView.setBackgroundColor(if (field) Color.LTGRAY else Color.TRANSPARENT)
        }

}