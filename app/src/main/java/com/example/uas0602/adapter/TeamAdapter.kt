package com.example.uas0602.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas0602.R
import com.example.uas0602.model.Teams
import com.squareup.picasso.Picasso

class TeamAdapter (private val ctx: Context?, private val items: List<Teams>, private val listener: (Teams) -> Unit):
        RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_team, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imgTeam = view.findViewById<ImageView>(R.id.imgTeam)
        val tvTeamName = view.findViewById<TextView>(R.id.tvTeamName)
        val tvTeamDesc = view.findViewById<TextView>(R.id.tvTeamDesc)

        fun bind(item: Teams, listener: (Teams) -> Unit) {
            tvTeamDesc.text = item.strDescriptionEN
            tvTeamName.text = item.strTeam
            item.strTeamBadge?.let { Picasso.get().load(it).into(imgTeam) }

            itemView.setOnClickListener { listener(item) }
        }
    }
}