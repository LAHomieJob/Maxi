package com.example.alexey.maxi.di.global.components

import com.example.alexey.maxi.di.components.RubricsFragmentComponent
import com.example.alexey.maxi.di.components.StocksFragmentComponent
import com.example.alexey.maxi.di.global.modules.*
import com.example.alexey.maxi.di.modules.rubricsScreen.RubricsFragmentModule
import com.example.alexey.maxi.di.modules.stocksScreen.StockFragmentModule
import com.example.alexey.maxi.presentation.mainScreen.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class,
        DatabaseModule::class, InterceptorModule::class,
        NetworkModule::class, NavigationModule::class))
interface AppComponent{
    fun inject(activity: MainActivity)

    fun plusRubricsFragmentComponent(module: RubricsFragmentModule): RubricsFragmentComponent
    fun plusStocksFragmentComponent(module: StockFragmentModule): StocksFragmentComponent
}