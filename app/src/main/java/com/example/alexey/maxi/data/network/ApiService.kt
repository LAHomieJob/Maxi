package com.example.alexey.maxi.data.network

import com.example.alexey.maxi.domain.models.Rubrics
import com.example.alexey.maxi.domain.models.Stock
import io.reactivex.Observable
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("offers?city code=arhangelsk")
    fun retrieveListOfStocks(): Observable<Stock>

    @GET("categories")
    fun retriveRubrics(): Deferred<Response<Rubrics>>
}