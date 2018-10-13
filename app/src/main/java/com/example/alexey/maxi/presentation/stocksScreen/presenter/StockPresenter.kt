package com.example.alexey.maxi.presentation.stocksScreen.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.alexey.maxi.domain.interactors.StockScreemInteractor
import com.example.alexey.maxi.presentation.base.BasePresenter
import com.example.alexey.maxi.presentation.stocksScreen.view.StockView
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router

@InjectViewState
class StockPresenter(
        private val router: Router,
        private val interactor: StockScreemInteractor,
        private val parentId: Int
) : BasePresenter<StockView>(router) {

    fun showListOfStocks() =
            interactor.retrieveListOfStockItemsSortedByRubrics(parentId).subscribeOn(AndroidSchedulers.mainThread()).subscribe({
                viewState.showStocks(it)
            })

}