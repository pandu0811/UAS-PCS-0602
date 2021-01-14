package com.example.uas0602.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas0602.R
import com.example.uas0602.model.Standings

class StandingAdapter(private val items: List<Standings>, private val ctx: Context?) :
    RecyclerView.Adapter<StandingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_standings, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvStandingName = view.findViewById<TextView>(R.id.tvStandingName)
        val tvStandingLoss = view.findViewById<TextView>(R.id.tvStandingLoss)
        val tvStandingWin = view.findViewById<TextView>(R.id.tvStandingWin)
        val tvStandingDraw = view.findViewById<TextView>(R.id.tvStandingDraw)

        fun bind(items: Standings) {

            tvStandingDraw.text = items.draw.toString()
            tvStandingLoss.text = items.loss.toString()
            tvStandingWin.text = items.win.toString()
            tvStandingName.text = items.name

        }
    }
}