package com.example.alexey.maxi.presentation.rubricsScreen.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.alexey.maxi.domain.models.Rubric

@StateStrategyType(AddToEndSingleStrategy::class)
interface RubricsView : MvpView {
    fun showListOfRubrics(list: List<Rubric>?)
    fun showError(message: String)
}