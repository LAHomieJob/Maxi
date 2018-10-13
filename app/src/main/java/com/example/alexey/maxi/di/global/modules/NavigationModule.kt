package com.example.alexey.maxi.di.global.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

@Module
class NavigationModule {

    val cicerone = Cicerone.create()

    @Provides
    @Singleton
    fun provideRouter() = cicerone.router

    @Provides
    @Singleton
    fun provideNavigationHolder() = cicerone.navigatorHolder
}