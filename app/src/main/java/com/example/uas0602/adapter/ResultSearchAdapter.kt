package com.example.uas0602.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas0602.R
import com.example.uas0602.activity.DetailMatchActivity
import com.example.uas0602.model.Match

class ResultSearchAdapter (private val context: Context?, private val items: List<Match>):
        RecyclerView.Adapter<ResultSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder( LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailMatchActivity::class.java)
            intent.putExtra("id", items[position].idEvent)
            intent.putExtra(DetailMatchActivity.ARGS_ID_AWAY,  items[position].idAwayTeam)
            intent.putExtra(DetailMatchActivity.ARGS_ID_HOME, items[position].idHomeTeam)
            context?.startActivity(intent)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvScoreHome = view.findViewById<TextView>(R.id.tvScoreHome)
        val tvScoreAway = view.findViewById<TextView>(R.id.tvScoreAway)
        val tvEvent = view.findViewById<TextView>(R.id.tvEvent)
        val tvDate = view.findViewById<TextView>(R.id.tvDate)

        fun bind(items: Match) {
            tvScoreHome.text = items.intHomeScore
            tvScoreAway.text = items.intAwayScore
            tvEvent.text = items.strEvent
            tvDate.text = items.dateEvent

        }
    }

}