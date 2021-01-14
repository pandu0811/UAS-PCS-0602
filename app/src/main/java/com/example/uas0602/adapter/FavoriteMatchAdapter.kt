package com.example.uas0602.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas0602.R
import com.example.uas0602.model.FavoriteMatchField

class FavoriteMatchAdapter (private val favoriteMatchField: List<FavoriteMatchField>, private val context: Context?, private val listener: (FavoriteMatchField) -> Unit) :
        RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int {
        return favoriteMatchField.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteMatchField[position], listener)
    }

}

class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val tvScoreHome = view.findViewById<TextView>(R.id.tvScoreHome)
    val tvScoreAway = view.findViewById<TextView>(R.id.tvScoreAway)
    val tvEvent = view.findViewById<TextView>(R.id.tvEvent)
    val tvDate = view.findViewById<TextView>(R.id.tvDate)

    fun bind(items: FavoriteMatchField, listener: (FavoriteMatchField) -> Unit) {
        tvScoreHome.text = items.intHomeScore
        tvScoreAway.text = items.intAwayScore
        tvEvent.text = items.strEvent
        tvDate.text = items.dateEvent
        itemView.setOnClickListener { listener(items) }
    }
}
