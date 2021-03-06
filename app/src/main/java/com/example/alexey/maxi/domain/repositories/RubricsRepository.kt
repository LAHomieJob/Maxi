package com.example.alexey.maxi.domain.repositories

import com.example.alexey.maxi.domain.models.Rubric
import kotlinx.coroutines.experimental.Deferred

interface RubricsRepository {
    fun retrieveListOfParentRubrics(): Deferred<List<Rubric>?>
}