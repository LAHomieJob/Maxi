package com.example.alexey.maxi.di.components

import com.example.alexey.maxi.di.global.modules.*
import com.example.alexey.maxi.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class,
        DatabaseModule::class, InterceptorModule::class,
        NetworkModule::class, NavigationModule::class))
interface AppComponent{
    fun inject(activity: MainActivity)

    fun plusRubricsFragmentComponent(): RubricsFragmentComponent
    fun plusStocksFragmentComponent()
}