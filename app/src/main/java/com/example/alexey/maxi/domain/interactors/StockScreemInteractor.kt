package com.example.alexey.maxi.domain.interactors

import com.example.alexey.maxi.domain.models.StockItem
import com.example.alexey.maxi.domain.repositories.StockRepository
import com.example.alexey.maxi.util.findCongruentElement
import io.reactivex.Observable
import io.reactivex.Observable.zip
import io.reactivex.functions.BiFunction

class StockScreemInteractor(private val repository: StockRepository) {

    fun retrieveListOfStockItemsSortedByRubrics(parentId: Int): Observable<List<StockItem>> = zip(
            repository.retrieveListOfChildRubricsIds(parentId).toObservable(),
            repository.retrieveListOfStocks(),
            BiFunction<List<Int>, List<StockItem>, List<StockItem>>
            { listRubricIds, listStockItem ->
                listStockItem.filter {
                    //Фильтрация списка
                    it.rubrics.findCongruentElement(listRubricIds)
                }.apply {
                    //Присвоение списка имен
                    forEach {
                        repository.retrieveListOfRubricsName(it.rubrics).subscribe { list -> it.rubricName = list }
                    }
                }
            }
    )
}