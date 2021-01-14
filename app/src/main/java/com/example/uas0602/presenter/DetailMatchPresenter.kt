package com.example.uas0602.presenter

import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.DetailMatchRespose
import com.example.uas0602.view.DetailMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailMatchPresenter (
    private val view: DetailMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getDetailMatch(idEvents: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getMatchDetails(idEvents)).await(),
                DetailMatchRespose::class.java
            )

            view.hideLoading()
            view.showDetailMatch(data.detailMatchResponse)
        }

    }
}