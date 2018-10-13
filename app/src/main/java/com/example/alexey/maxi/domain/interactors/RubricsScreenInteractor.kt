package com.example.alexey.maxi.domain.interactors

import com.example.alexey.maxi.domain.repositories.RubricsRepository

class RubricsScreenInteractor(private val repository: RubricsRepository) {

    /*
     * Метод возвращает список родитекльских рубрик
    **/
    suspend fun retrieveListOfParentRubrics() =
            repository.retrieveListOfParentRubrics()

}