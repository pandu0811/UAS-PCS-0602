package com.example.uas0602.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.uas0602.R
import com.example.uas0602.activity.DetailLigaActivity
import com.example.uas0602.adapter.StandingAdapter
import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.Standings
import com.example.uas0602.presenter.StandingPresenter
import com.example.uas0602.view.StandingView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_standings.*

/**
 * A simple [Fragment] subclass.
 */
class StandingsFragment : Fragment(), StandingView {

    private lateinit var presenter: StandingPresenter
    private var items: MutableList<Standings> = mutableListOf()
    private lateinit var adapter: StandingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = StandingPresenter(this, apiRepository, gson)
        presenter.getStanding(DetailLigaActivity.idLeague)

        adapter = StandingAdapter(items, context)
        rvStanding.layoutManager = LinearLayoutManager(context)
        rvStanding.adapter = adapter
    }

    override fun getStanding(data: List<Standings>) {
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
    }


}
