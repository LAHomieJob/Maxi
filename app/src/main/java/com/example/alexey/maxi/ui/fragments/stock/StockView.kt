package com.example.alexey.maxi.ui.fragments.stock

import com.arellomobile.mvp.MvpView
import com.example.alexey.maxi.data.network.StockItem

interface StockView : MvpView {
    fun showStocks(list: List<StockItem>)
    fun showError(errorMsg: String)
}