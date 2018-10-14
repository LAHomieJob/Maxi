package com.example.alexey.maxi.presentation.stocksScreen.view

import com.arellomobile.mvp.MvpView
import com.example.alexey.maxi.domain.models.StockItem

interface StockView : MvpView {
    fun showStocks(list: List<StockItem>)
    fun showError(errorMsg: String)
}