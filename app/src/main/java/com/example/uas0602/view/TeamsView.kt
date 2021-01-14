package com.example.uas0602.view

import com.example.uas0602.model.Teams

interface TeamsView {
    fun showTeams(homeTeams: List<Teams>, awayTeams: List<Teams>)
}
