package com.example.alexey.maxi.presentation.rubricsScreen.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.alexey.maxi.domain.interactors.RubricsScreenInteractor
import com.example.alexey.maxi.presentation.base.BasePresenter
import com.example.alexey.maxi.presentation.navigation.ScreenKeys
import com.example.alexey.maxi.presentation.rubricsScreen.view.RubricsView
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import ru.terrakok.cicerone.Router

@InjectViewState
class RubricsPresenter(
        private val router: Router,
        private val rubricsScreenInteractor: RubricsScreenInteractor
) : BasePresenter<RubricsView>(router) {

    fun showParentRubrics() = GlobalScope.launch(Dispatchers.Main) {
        rubricsScreenInteractor.retrieveListOfParentRubrics().await().let { viewState.showListOfRubrics(it) }
    }

    fun navigateToStockScreen(parentId: Int) = router.navigateTo(ScreenKeys.STOCK_SCREEN, parentId)

}