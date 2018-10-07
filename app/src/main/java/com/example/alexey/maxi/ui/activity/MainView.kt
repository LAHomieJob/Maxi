package com.example.alexey.maxi.ui.activity

import com.arellomobile.mvp.MvpView
import com.example.alexey.maxi.model.StockItem

interface MainView : MvpView {
    fun showStocks(list: List<StockItem>)
    fun showError(errorMsg: String)
}