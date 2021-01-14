package com.example.uas0602.presenter

import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.MatchResponse
import com.example.uas0602.view.PreviousMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PreviousMatchPresenter (
    private val view: PreviousMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getPreviousMatch(idLeague: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getPreviousMatch(idLeague)).await(),
                MatchResponse::class.java
            )
                view.hideLoading()
                view.showPreviousMatch(data.matchResponse)

        }

    }
}