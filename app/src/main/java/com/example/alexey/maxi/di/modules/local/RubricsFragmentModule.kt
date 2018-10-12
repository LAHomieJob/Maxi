package com.example.alexey.maxi.di.modules.local

import com.example.alexey.maxi.data.network.ApiService
import com.example.alexey.maxi.di.scopes.FragmentScope
import com.example.alexey.maxi.ui.fragments.rubrics.RubricsPresenter
import dagger.Module
import dagger.Provides

@Module
class RubricsFragmentModule {
    @Provides
    @FragmentScope
    fun provideFragmentPresenter(apiService: ApiService) = RubricsPresenter(apiService)
}