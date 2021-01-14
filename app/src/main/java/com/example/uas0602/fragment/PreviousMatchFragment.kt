package com.example.uas0602.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.uas0602.R
import com.example.uas0602.activity.DetailLigaActivity
import com.example.uas0602.activity.DetailMatchActivity
import com.example.uas0602.adapter.PreviousMatchAdapter
import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.Match
import com.example.uas0602.presenter.PreviousMatchPresenter
import com.example.uas0602.utils.invisible
import com.example.uas0602.utils.visible
import com.example.uas0602.view.PreviousMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_list_previousmatch.*
import org.jetbrains.anko.startActivity

class PreviousMatchFragment : Fragment(), PreviousMatchView {



    lateinit var presenter: PreviousMatchPresenter
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var adapter: PreviousMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_previousmatch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = PreviousMatchPresenter(this, apiRepository, gson)
        presenter.getPreviousMatch(DetailLigaActivity.idLeague)

        adapter = PreviousMatchAdapter(context, match) {
            context?.startActivity<DetailMatchActivity>("id" to "${it.idEvent}",
                DetailMatchActivity.ARGS_ID_AWAY to "${it.idAwayTeam}",
                DetailMatchActivity.ARGS_ID_HOME to "${it.idHomeTeam}")
        }

        rvPreviousMatch.layoutManager = LinearLayoutManager(context)
        rvPreviousMatch.adapter = adapter
    }

    override fun showPreviousMatch(data: List<Match>) {
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBarPrevious.visible()
    }

    override fun hideLoading() {
        progressBarPrevious.invisible()
    }
}
