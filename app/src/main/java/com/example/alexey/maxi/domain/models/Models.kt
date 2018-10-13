package com.example.alexey.maxi.domain.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Модель Rubric используется для сериализации сетевых запросов и хранения в базе данных
 * */
@Entity(tableName = "rubrics")
data class Rubric(

        @PrimaryKey
        val id: Int,

        val name: String,

        @ColumnInfo(name = "parent_rubric_id")
        @SerializedName("parent_rubric_id")
        val parentRubricId: Int?)

data class Rubrics(val response: List<Rubric>)

data class StockItem(val title: String,
                     val image: String,
                     val rubrics: List<Int>,
                     @SerializedName("price_new") val priceNew: Double) {

    @Expose(serialize = false, deserialize = false)
    var rubricName: List<String>? = null

}

data class Stock(val response: List<StockItem>)
