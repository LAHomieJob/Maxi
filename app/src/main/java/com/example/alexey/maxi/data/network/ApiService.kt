package com.example.alexey.maxi.data.network

import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("offers?city code=arhangelsk")
    fun retrieveListOfStocks(): Deferred<Response<Stock>>

    @GET("categories")
    fun retriveCategories(): Deferred<Response<Rubrics>>
}