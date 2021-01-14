package com.example.uas0602.presenter

import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.MatchResponse
import com.example.uas0602.view.UpcomingMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpcomingPresenter (
    private val view: UpcomingMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getUpcomingMatch(idLeague: String?) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getUpcomingMatch(idLeague)).await(),
                MatchResponse::class.java
            )

                view.showUpcomingMatch(data.matchResponse)

        }

    }
}

