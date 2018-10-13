package com.example.alexey.maxi.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.alexey.maxi.domain.models.Rubric
import io.reactivex.Observable

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRubrics(list: List<Rubric>)

    @Query("SELECT * FROM rubrics WHERE parent_rubric_id = null")
    fun selectAllParentRubrics(): List<Rubric>?

    //Запрос возвращает список id-шников дочерних рубрик
    @Query("SELECT id FROM rubrics WHERE parent_rubric_id = :parentId")
    fun selectChildRubricsByIdParentId(parentId: Int): Observable<List<Int>>

    //Запрос возвращает список наименований рубрик по массиву из id
    @Query("SELECT name FROM rubrics where id IN (:listId)")
    fun selectRubricsName(listId: List<Int>): Observable<List<String>>
}