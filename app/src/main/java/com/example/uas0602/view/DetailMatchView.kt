package com.example.uas0602.view

import com.example.uas0602.model.DetailMatch

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(data: List<DetailMatch?>?)
}