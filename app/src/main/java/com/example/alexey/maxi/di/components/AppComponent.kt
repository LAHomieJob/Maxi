package com.example.alexey.maxi.di.components

import com.example.alexey.maxi.di.global.modules.AppModule
import com.example.alexey.maxi.di.global.modules.NetworkModule
import com.example.alexey.maxi.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class,
        NetworkModule::class, PresenterModule::class))
interface AppComponent{
    fun inject(activity: MainActivity)

    fun plusRubricsFragmentComponent(): RubricsFragmentComponent
    fun plusStocksFragmentComponent()
}