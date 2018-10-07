package com.example.alexey.maxi.model

import com.google.gson.annotations.SerializedName

data class StockItem(val title: String,
                     val image: String,
                     val rubrics: List<Int>,
                     @SerializedName("price_new")val priceNew: Double)

data class Stock(val response: List<StockItem>)