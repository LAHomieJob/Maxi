package com.example.alexey.maxi.data.repository

import com.example.alexey.maxi.data.database.DaoMaxi
import com.example.alexey.maxi.data.network.ApiService
import com.example.alexey.maxi.domain.repositories.RubricsRepository
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async

class RubricsRepositoryImpl(val apiService: ApiService,
                            val dao: DaoMaxi) : RubricsRepository {

    override fun retrieveListOfParentRubrics() = GlobalScope.async(Dispatchers.IO) {
        var dbRubricsList = dao.selectAllParentRubrics()
        if (dbRubricsList != null) {
            //Если в БД отсутствуют рубрики, то получить из сети и поместить в БД
            apiService.retriveRubrics().await().body()?.response.let { dao.insertAllRubrics(it!!) }
            dbRubricsList = dao.selectAllParentRubrics()
        }
        dbRubricsList
    }
}