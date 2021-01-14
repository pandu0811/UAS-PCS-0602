package com.example.uas0602.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas0602.R
import com.example.uas0602.model.FavoriteTeamField
import com.squareup.picasso.Picasso

class FavoriteTeamAdapter(private val item: List<FavoriteTeamField>, private val context: Context?, private val listener: (FavoriteTeamField) -> Unit):
        RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false))

    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position], listener)
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgTeam = view.findViewById<ImageView>(R.id.imgTeam)
        val tvTeamName = view.findViewById<TextView>(R.id.tvTeamName)
        val tvTeamDesc = view.findViewById<TextView>(R.id.tvTeamDesc)

        fun bind(item: FavoriteTeamField, listener: (FavoriteTeamField) -> Unit) {
             tvTeamDesc.text = item.strDescriptionEN
            tvTeamName.text = item.strTeam
            item.strTeamBadge?.let { Picasso.get().load(it).into(imgTeam) }
            itemView.setOnClickListener { listener(item) }
        }
    }

}