package com.example.uas0602.presenter

import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.TeamsResponse
import com.example.uas0602.view.ListTeamView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TeamListPresenter (
    private val view: ListTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getListTeam(idLeague: String?) {

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getTeamList(idLeague)).await(),
                TeamsResponse::class.java)

            view.showListTeam(data.teamsResponse)
        }
    }
}