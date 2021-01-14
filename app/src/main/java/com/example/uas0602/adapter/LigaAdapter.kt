package com.example.uas0602.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas0602.R
import com.example.uas0602.activity.DetailLigaActivity
import com.example.uas0602.model.Liga
import com.squareup.picasso.Picasso


class LigaAdapter(private val context: Context?, private val liga: List<Liga>):
        RecyclerView.Adapter<LigaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_liga, parent, false))
    }

    override fun getItemCount(): Int {
        return liga.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(liga[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailLigaActivity::class.java)
            intent.putExtra("keyDetail", liga[position])
            context?.startActivity(intent)

        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imgLiga = view.findViewById<ImageView>(R.id.imgLiga)
        private val tvLiga = view.findViewById<TextView>(R.id.tvLiga)

        fun bind(liga: Liga) {
            tvLiga.text = liga.name
            liga.image?.let { Picasso.get().load(it).into(imgLiga) }
        }
    }

}