package com.example.alexey.maxi.domain.interactors

import com.example.alexey.maxi.domain.models.StockItem
import com.example.alexey.maxi.domain.repositories.StockRepository
import com.example.alexey.maxi.util.findCongruentElement
import io.reactivex.Observable.zip
import io.reactivex.functions.BiFunction

class StockScreemInteractor(val repository: StockRepository) {

    fun retrieveListOfStockItemsSortedByRubrics(parentId: Int) = zip(
            repository.retrieveListOfChildRubricsIds(parentId),
            repository.retrieveListOfStocks(),
            BiFunction<List<Int>, List<StockItem>, List<StockItem>>
            { listRubric, listStockItem ->
                listStockItem.filter {
                    //Фильтрация списка
                    it.rubrics.findCongruentElement(listRubric)
                }.apply {
                    //Присвоение списка имен
                    forEach {
                        repository.retrieveListOfRubricsName(it.rubrics).subscribe { list -> it.rubricName = list }
                    }
                }
            }
    )
}