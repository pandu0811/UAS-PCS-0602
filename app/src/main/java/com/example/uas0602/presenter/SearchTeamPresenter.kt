package com.example.uas0602.presenter

import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.SearchTeamResponse
import com.example.uas0602.view.SearchTeamView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter (
    private val view: SearchTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getSearchTeam(teamName: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getSearchTeam(teamName)).await(),
                SearchTeamResponse::class.java
            )

            view.hideLoading()
            view.getSearchTeam(data.searchTeamResponse)

        }
    }
}