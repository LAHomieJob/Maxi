package com.example.alexey.maxi.network

import com.example.alexey.maxi.model.Stock
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("offers?city code=arhangelsk")
    fun retrieveListOfStocks(): Deferred<Response<Stock>>

}