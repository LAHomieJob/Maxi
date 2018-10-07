package com.example.alexey.maxi.di.modules

import com.example.alexey.maxi.network.ApiService
import com.example.alexey.maxi.ui.activity.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun provideActivityPresenter(apiService: ApiService) = MainPresenter(apiService)
}