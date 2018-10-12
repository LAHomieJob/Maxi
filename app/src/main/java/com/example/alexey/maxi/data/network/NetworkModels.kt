package com.example.alexey.maxi.data.network

import com.google.gson.annotations.SerializedName

data class StockItem(val title: String,
                     val image: String,
                     val rubrics: List<Int>,
                     @SerializedName("price_new") val priceNew: Double)

data class Stock(val response: List<StockItem>) {

    fun getListOfRelatedStocks(listRubricsId: List<Int>): List<StockItem> {
        return response.filter {
            listRubricsId.containsAll(it.rubrics)
        }
    }
}

data class RubrickItem(val id: Int,
                       val name: String,
                       @SerializedName("parent_rubric_id") val parentRubricId: Int?)

data class Rubrics(val response: List<RubrickItem>) {

    //Метод возвращает список родительских рубрик, которотых parent_rubric_id равняется null
    fun getOnlyParentRubrics() = response.filter {
        it.parentRubricId == null
    }

    /**
     * @param parentId идентификатор родительсткой группы
     * @return список идентификаторов групп имеющих общий родительский идентификатор
     * */
    fun getListOfChildRubrics(parentId: Int) =
            response.groupBy {
                it.parentRubricId
            }.get(parentId)?.map {
                it.id
            }
}

