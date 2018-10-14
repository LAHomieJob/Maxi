package com.example.alexey.maxi.di.components

import com.example.alexey.maxi.di.modules.stocksScreen.StockFragmentModule
import com.example.alexey.maxi.di.scopes.FragmentScope
import com.example.alexey.maxi.presentation.stocksScreen.view.StockFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(StockFragmentModule::class))
@FragmentScope
interface StocksFragmentComponent {
    fun inject(fragment: StockFragment)
}