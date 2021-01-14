package com.example.uas0602.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas0602.R
import com.example.uas0602.model.Match

class UpcomingMatchAdapter (private val context: Context?, private val items: List<Match>, private val listener: (Match) -> Unit):
        RecyclerView.Adapter<UpcomingMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_upcoming_match, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val tvEvent = view.findViewById<TextView>(R.id.tvEvent)
        val tvDate = view.findViewById<TextView>(R.id.tvDate)

        fun bind(items: Match, listener: (Match) -> Unit) {

            tvEvent.text = items.strEvent
            tvDate.text = items.dateEvent
            itemView.setOnClickListener { listener(items) }

        }
    }

}