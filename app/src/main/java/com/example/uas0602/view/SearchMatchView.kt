package com.example.uas0602.view

import com.example.uas0602.model.Match

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun getMatch(data: List<Match>)
}