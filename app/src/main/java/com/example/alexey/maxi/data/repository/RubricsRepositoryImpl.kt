package com.example.alexey.maxi.data.repository

import com.example.alexey.maxi.data.database.Dao
import com.example.alexey.maxi.data.network.ApiService
import com.example.alexey.maxi.domain.repositories.RubricsRepository
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async

class RubricsRepositoryImpl(val apiService: ApiService,
                            val dao: Dao) : RubricsRepository {

    override suspend fun retrieveListOfParentRubrics() = GlobalScope.async(Dispatchers.IO) {
        var list = dao.selectAllParentRubrics()
        if (list != null) {
            list = apiService.retriveRubrics().await().body()!!.response
            dao.insertAllRubrics(list)
        }
        list
    }
}