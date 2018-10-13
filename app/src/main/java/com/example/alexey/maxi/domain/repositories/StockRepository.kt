package com.example.alexey.maxi.domain.repositories

import com.example.alexey.maxi.domain.models.StockItem
import io.reactivex.Observable
import io.reactivex.Single

interface StockRepository {
    fun retrieveListOfChildRubricsIds(parenId: Int): Single<List<Int>>
    fun retrieveListOfRubricsName(list: List<Int>): Single<List<String>>
    fun retrieveListOfStocks(): Observable<List<StockItem>>
}