package com.example.uas0602.view

import com.example.uas0602.model.Standings

interface StandingView {
    fun getStanding(data: List<Standings>)
}