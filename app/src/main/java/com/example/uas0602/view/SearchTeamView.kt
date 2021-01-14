package com.example.uas0602.view

import com.example.uas0602.model.Teams

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun getSearchTeam(data: List<Teams>)
}