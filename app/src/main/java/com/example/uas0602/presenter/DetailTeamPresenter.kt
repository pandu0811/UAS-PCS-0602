package com.example.uas0602.presenter

import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.DetailTeamResponse
import com.example.uas0602.view.DetailTeamView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailTeamPresenter(
    private val view: DetailTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getTeamDetail(idTeam: String?) {

        GlobalScope.launch (Dispatchers.Main) {

            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getTeamDetails(idTeam)).await(),
                DetailTeamResponse::class.java)

            view.showTeamDetail(data.detailTeamsResponse)
        }
    }
}
