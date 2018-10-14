package com.example.alexey.maxi.di.modules.rubricsScreen

import com.example.alexey.maxi.data.database.DaoMaxi
import com.example.alexey.maxi.data.network.ApiService
import com.example.alexey.maxi.data.repository.RubricsRepositoryImpl
import com.example.alexey.maxi.di.scopes.FragmentScope
import com.example.alexey.maxi.domain.interactors.RubricsScreenInteractor
import com.example.alexey.maxi.presentation.rubricsScreen.presenter.RubricsPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class RubricsFragmentModule {

    @Provides
    @FragmentScope
    fun provideFragmentPresenter(
            router: Router,
            rubricsScreenInteractor: RubricsScreenInteractor
    ) = RubricsPresenter(router, rubricsScreenInteractor)

    @Provides
    @FragmentScope
    fun provideFragmentInteractor(repository: RubricsRepositoryImpl) =
            RubricsScreenInteractor(repository)

    @Provides
    @FragmentScope
    fun provideFragmentRepository(apiService: ApiService, dao: DaoMaxi) =
            RubricsRepositoryImpl(apiService, dao)

}