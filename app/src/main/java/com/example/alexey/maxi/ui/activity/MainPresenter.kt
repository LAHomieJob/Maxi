package com.example.alexey.maxi.ui.activity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.alexey.maxi.network.ApiService
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

@InjectViewState
class MainPresenter(val apiService: ApiService) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadStocks()
    }

    private fun loadStocks() =
            try {
                GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT, null, {
                    apiService.retrieveListOfStocks().await().body()?.let {
                        viewState.showStocks(it.response)
                    }
                })
            } catch (e: Exception) {
                viewState.showError("Ошибка загрузки")
            }
}