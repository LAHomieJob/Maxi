package com.example.alexey.maxi.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRubrics(list: List<Rubric>)

    @Query("SELECT * FROM rubrics WHERE parent_rubric_id = null")
    fun selectAllParentRubrics(): List<Rubric>

    @Query("SELECT * FROM rubrics WHERE parent_rubric_id = :parentId")
    fun selectChildRubricsByParentId(parentId: Int): List<Rubric>
}