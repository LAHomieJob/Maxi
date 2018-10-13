package com.example.alexey.maxi.domain.repositories

import com.example.alexey.maxi.domain.models.StockItem
import io.reactivex.Observable

interface StockRepository {
    fun retrieveListOfChildRubricsIds(parenId: Int): Observable<List<Int>>
    fun retrieveListOfRubricsName(list: List<Int>): Observable<List<String>>
    fun retrieveListOfStocks(): Observable<List<StockItem>>
}