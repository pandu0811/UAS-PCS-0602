package com.example.uas0602.presenter

import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.StandingsResponse
import com.example.uas0602.view.StandingView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingPresenter (
    private val view: StandingView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getStanding(idLeague: String?) {


        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getStandings(idLeague)).await(),
                StandingsResponse::class.java
            )

            view.getStanding(data.standingsResponse)
        }
    }
}