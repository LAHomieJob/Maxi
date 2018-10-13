package com.example.alexey.maxi.ui.fragments.rubrics

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RubricsView : MvpView {
    fun showListOfRubrics(list: List<RubrickItem>)
    fun showError(message: String)
}