package com.example.alexey.maxi.di.modules.components

import com.example.alexey.maxi.di.modules.AppModule
import com.example.alexey.maxi.di.modules.NetworkModule
import com.example.alexey.maxi.di.modules.PresenterModule
import com.example.alexey.maxi.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class,
        NetworkModule::class, PresenterModule::class))
interface AppComponent{
    fun inject(activity: MainActivity)
}