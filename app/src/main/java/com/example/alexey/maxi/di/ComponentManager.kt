package com.example.alexey.maxi.di

import android.content.Context
import com.example.alexey.maxi.di.components.AppComponent
import com.example.alexey.maxi.di.global.modules.AppModule

class ComponentManager(context: Context) {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().
                appModule(AppModule(context)).
                build()
    }

    fun provideRubricsFragmentComponent() = appComponent.plusRubricsFragmentComponent()

    fun provideStockFragmentComponent() = appComponent.plusStocksFragmentComponent()
}