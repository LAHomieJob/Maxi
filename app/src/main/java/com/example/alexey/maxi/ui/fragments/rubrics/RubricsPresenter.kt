package com.example.alexey.maxi.ui.fragments.rubrics

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.alexey.maxi.data.network.ApiService
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

@InjectViewState
class RubricsPresenter(val apiService: ApiService) : MvpPresenter<RubricsView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRootRubrics()
    }

    private fun loadRootRubrics() =
            try {
                GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT, null, {
                    apiService.retriveRubrics().await().body()?.let {
                        //Get the list of root rubrics with null parent
                        val filteredList = it.getOnlyParentRubrics()
                        viewState.showListOfRubrics(filteredList)
                    }
                })
            } catch (e: Exception) {
                viewState.showError("Ошибка загрузки")
            }
}