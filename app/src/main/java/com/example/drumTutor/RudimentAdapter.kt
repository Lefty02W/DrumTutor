package com.example.drumTutor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RudimentAdapter(
    val context: Context,
    val rudiments: Array<Rudiment>,
    val clickListener: (Rudiment) -> Unit
) : RecyclerView.Adapter<RudimentsViewHolder>() {
    override fun getItemCount(): Int = rudiments.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RudimentsViewHolder {
        val inflater = LayoutInflater.from(context)
        //todo check using right layout
        val view = inflater.inflate(R.layout.rudiment_item, parent, false)
        val holder = RudimentsViewHolder(view)
        view.setOnClickListener {
            clickListener(rudiments[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: RudimentsViewHolder, i: Int) {
        holder.rudimentText.text = rudiments[i].name
    }
}