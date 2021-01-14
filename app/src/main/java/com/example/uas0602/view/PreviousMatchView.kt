package com.example.uas0602.view

import com.example.uas0602.model.Match

interface PreviousMatchView {
    fun showLoading()
    fun hideLoading()
    fun showPreviousMatch(data: List<Match>)
}