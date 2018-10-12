package com.example.alexey.maxi.ui.fragments.stock

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.alexey.maxi.data.network.ApiService
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

@InjectViewState
class StockPresenter(val apiService: ApiService) : MvpPresenter<StockView>() {

    private fun loadStocks(listParentRubricId: List<Int>) =
            try {
                GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT, null, {
                    apiService.retrieveListOfStocks().await().body()?.let {
                        val list = it.getListOfRelatedStocks(listParentRubricId)
                        viewState.showStocks(list)
                    }
                })
            } catch (e: Exception) {
                viewState.showError("Ошибка загрузки")
            }
}