package com.example.uas0602.presenter

import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.MatchResponse
import com.example.uas0602.view.SearchMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter (
    private val view: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getSearchMatch(teamsName: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getSearchMatch(teamsName)).await(),
                MatchResponse::class.java
            )

                view.hideLoading()
                view.getMatch(data.searchMatchResponse.filter { it.strSport.equals("Soccer")})

        }

    }
}