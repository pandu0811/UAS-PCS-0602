package com.example.uas0602.view

import com.example.uas0602.model.Match


interface UpcomingMatchView {
    fun showUpcomingMatch(data: List<Match>)
}