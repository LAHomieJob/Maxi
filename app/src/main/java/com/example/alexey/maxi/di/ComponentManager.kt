package com.example.alexey.maxi.di

import android.content.Context
import com.example.alexey.maxi.di.components.DaggerAppComponent
import com.example.alexey.maxi.di.global.components.AppComponent
import com.example.alexey.maxi.di.global.modules.AppModule
import com.example.alexey.maxi.di.modules.rubricsScreen.RubricsFragmentModule
import com.example.alexey.maxi.di.modules.stocksScreen.StockFragmentModule

class ComponentManager(context: Context) {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().
                appModule(AppModule(context)).
                build()
    }

    fun provideRubricsFragmentComponent() = appComponent.plusRubricsFragmentComponent(RubricsFragmentModule())

    fun provideStockFragmentComponent(parentId: Int) =
            appComponent.plusStocksFragmentComponent(StockFragmentModule(parentId))
}