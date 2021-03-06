package com.example.alexey.maxi.data.repository

import com.example.alexey.maxi.data.database.DaoMaxi
import com.example.alexey.maxi.data.network.ApiService
import com.example.alexey.maxi.domain.models.StockItem
import com.example.alexey.maxi.domain.repositories.StockRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class StockRepositoryImpl(val apiService: ApiService, val dao: DaoMaxi) : StockRepository {

    override fun retrieveListOfChildRubricsIds(parenId: Int) =
            dao.selectChildRubricsByIdParentId(parenId).subscribeOn(Schedulers.io())

    override fun retrieveListOfRubricsName(list: List<Int>) =
            dao.selectRubricsName(list)

    override fun retrieveListOfStocks(): Observable<List<StockItem>> =
            apiService.retrieveListOfStocks().subscribeOn(Schedulers.io()).map { it.response }

}


