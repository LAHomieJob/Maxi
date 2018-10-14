package com.example.alexey.maxi.di.modules.stocksScreen

import com.example.alexey.maxi.data.database.DaoMaxi
import com.example.alexey.maxi.data.network.ApiService
import com.example.alexey.maxi.data.repository.StockRepositoryImpl
import com.example.alexey.maxi.di.scopes.FragmentScope
import com.example.alexey.maxi.domain.interactors.StockScreemInteractor
import com.example.alexey.maxi.presentation.stocksScreen.presenter.StockPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class StockFragmentModule(val parentId: Int) {

    @Provides
    @FragmentScope
    fun provideFragmentPresenter(
            router: Router,
            stocksScreenInteractor: StockScreemInteractor
    ) = StockPresenter(router, stocksScreenInteractor, parentId)

    @Provides
    @FragmentScope
    fun provideFragmentInteractor(repository: StockRepositoryImpl) =
            StockScreemInteractor(repository)

    @Provides
    @FragmentScope
    fun provideFragmentRepository(apiService: ApiService, dao: DaoMaxi) =
            StockRepositoryImpl(apiService, dao)

}